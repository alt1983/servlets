package ru.netology.repository;

import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

// Stub
public class PostRepository {

    private List<Post> posts;
    private volatile int count;

    public PostRepository() {
        posts = Collections.synchronizedList(new ArrayList());
        count = 0;
        this.save(new Post(0, "Начало"));
    }

    public List<Post> all() {
        return posts;
    }

    public Optional<Post> getById(long id) {
        for (Post p : posts) {
            if (p.getId() == id) {
                return Optional.ofNullable(p);
            }
        }
        return Optional.empty();
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            post.setId(count);
            posts.add(post);
            count++;
            return post;
        } else {

            for (Post p : posts) {
                if (p.getId() == post.getId()) {
                    p.setContent(post.getContent());
                    return post;
                }
            }
            post.setId(count);
            posts.add(post);
            count++;
            return post;
        }
    }

    public void removeById(long id) {
        int i = 0;
        for (Post p : posts) {
            if (p.getId() == id) {
                posts.remove(i);
            }
            i++;
        }
    }
}
