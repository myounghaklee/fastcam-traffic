package com.example.fastcampusmysql.domain.post.repository;

import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class PostRepository {

    static final String TABLE = "Post";
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final RowMapper<DailyPostCount> DAILY_POST_COUNT_ROW_MAPPER = (ResultSet resultSet, int rowNum) -> new DailyPostCount(
            resultSet.getLong("memberId"),
            resultSet.getObject("createdDate", LocalDate.class),
            resultSet.getLong("count")
    );

    private static final RowMapper<Post> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> Post.builder()
            .id(resultSet.getLong("id"))
            .memberId(resultSet.getLong("memberId"))
            .contents(resultSet.getString("contents"))
            .createdDate(resultSet.getObject("createdDate", LocalDate.class))
            .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
            .build();


    public List<DailyPostCount> groupByCreatedDate(DailyPostCountRequest request) {
        var sql = String.format("""
                SELECT createdDate, memberId, count(id)
                FROM %s
                WHERE memberId = :memberId and createdDate between :firstDate and :lastDate
                GROUP BY memberid, createdDate 
                """, TABLE);
        var params = new BeanPropertySqlParameterSource(request);
        return namedParameterJdbcTemplate.query(sql, params, DAILY_POST_COUNT_ROW_MAPPER);
    }

    public Page<Post> findAllByMemberId(Long memberId, Pageable pageRequest){
        var sql = String.format("""
                SELECT * 
                FROM POST
                WHERE memberId = :memberId
                LIMIT :size
                OFFSET :offset
                """, TABLE);
        var params = new MapSqlParameterSource()
                .addValue("memberId", memberId)
                .addValue("size", pageRequest.getPageSize())
                .addValue("offset", pageRequest.getOffset());

        var post = namedParameterJdbcTemplate.query(sql,params,ROW_MAPPER);

        return new PageImpl<>(post, pageRequest,getCount(memberId));
    }

    private  Integer getCount(Long memberId){
        var sql = String.format("""
                SELECT COUNT(id)
                FROM %s
                WHERE memberId = :memberId
                """, TABLE);
        var params = new MapSqlParameterSource().addValue("memberId", memberId);
        return namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
    }

    public List<Post> findAllByMemberIdAndOrderByIdDesc(Long memberId, int size){
        var sql = String.format("""
                SELECT COUNT(id)
                FROM %s
                WHERE memberId = :memberId
                ORBEr BY id DESC
                LIMIT :size
                """, TABLE);
        var params = new MapSqlParameterSource()
                .addValue("memberId", memberId)
                .addValue("size", size);
        return namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);
    }

    public List<Post> findAllByLessThanIdANdMemberIdAndOrderByIdDesc(Long id, Long memberId, int size){
        var sql = String.format("""
                SELECT COUNT(id)
                FROM %s
                WHERE memberId = :memberId and id< :id
                ORBEr BY id DESC
                LIMIT :size
                """, TABLE);
        var params = new MapSqlParameterSource()
                .addValue("memberId", memberId)
                .addValue("id", id)
                .addValue("size", size);
        return namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);
    }

    public Post save(Post post) {
        if (post.getId() == null) {
            return insert(post);
        }
        throw new UnsupportedOperationException("Post는 갱신을 지원하지 않습니다.");
    }

    private Post insert(Post post) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new BeanPropertySqlParameterSource(post);
        var id = jdbcInsert.executeAndReturnKey(params).longValue();

        return Post.builder()
                .id(id)
                .memberId(post.getMemberId())
                .contents(post.getContents())
                .createdDate(post.getCreatedDate())
                .createdAt(post.getCreatedAt())
                .build();
    }
}
