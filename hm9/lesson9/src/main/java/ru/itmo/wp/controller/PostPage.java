package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.form.CommentCredentials;
import ru.itmo.wp.form.validator.CommentCredentialsValidator;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.PostService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PostPage extends Page {
    private final PostService postService;
    private final CommentCredentialsValidator commentCredentialsValidator;

    public PostPage(PostService postService, CommentCredentialsValidator commentCredentialsValidator) {
        this.postService = postService;
        this.commentCredentialsValidator = commentCredentialsValidator;
    }

    public Post findPost(String id, HttpSession httpSession) {
        try {
            Post post = postService.findById(Long.parseLong(id));
            if (post != null) {
                return post;
            }
        } catch (NumberFormatException e) {
            putMessage(httpSession, "Invalid post id");
        }
        return null;
    }

    @InitBinder("commentForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(commentCredentialsValidator);
    }

    @Guest
    @GetMapping("/post/{id}")
    public String writeCommentGet(@PathVariable("id") String id, Model model, HttpSession httpSession) {
        Post post = findPost(id, httpSession);

        if (post == null) {
            putMessage(httpSession, "No such post or invalid post id");
            return "redirect:/";
        } else {
            model.addAttribute("post", post);
        }

        if (getUser(httpSession) != null) {
            model.addAttribute("commentForm", new CommentCredentials());
        }
        return "PostPage";
    }

    @PostMapping("/post/{id}")
    public String writeCommentPost(@PathVariable("id") String id, @Valid @ModelAttribute("commentForm") CommentCredentials commentForm, Model model,
                                   BindingResult bindingResult, HttpSession httpSession) {
        Post post = findPost(id, httpSession);

        if (post == null) {
            return "redirect:/";
        } else {
            model.addAttribute("post", post);
        }

        if (bindingResult.hasErrors()) {
            return "PostPage";
        }

        Comment comment = new Comment();
        comment.setText(commentForm.getText());
        comment.setUser(getUser(httpSession));
        comment.setPost(post);

        postService.updatePost(post, comment);

        return "redirect:/post/" + post.getId();
    }
}
