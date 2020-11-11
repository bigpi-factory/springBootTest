package com.java.springboottest.first.service.posts;
import com.java.springboottest.first.domain.posts.Posts;
import com.java.springboottest.first.domain.posts.PostsRepository;
import com.java.springboottest.first.web.dto.PostsResponseDto;
import com.java.springboottest.first.web.dto.PostsSaveRequestDto;
import com.java.springboottest.first.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당게시글이 업습니다. id="+ id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(("해당게시글이 없습니다. id =" + id)));

        return new PostsResponseDto(entity);
    }
}
