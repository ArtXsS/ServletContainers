
package ru.netology.repository;

import ru.netology.model.Post;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class PostRepository {
    private final List<Post> posts = new CopyOnWriteArrayList<>();
    private final AtomicLong postIdGenerator = new AtomicLong(0);

    public List<Post> all() {
        return posts;
    }

    public Optional<Post> getById(long id) {
        return posts.stream()
                .filter(post -> post.getId() == id)
                .findFirst();
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            long newId = postIdGenerator.incrementAndGet();
            post.setId(newId);
            posts.add(post);
            return post;
        } else {
            for (int i = 0; i < posts.size(); i++) {
                if (posts.get(i).getId() == post.getId()) {
                    posts.set(i, post);
                    return post;
                }
            }
            return null;
        }
    }

    public void removeById(long id) {
        posts.removeIf(post -> post.getId() == id);
    }
}
