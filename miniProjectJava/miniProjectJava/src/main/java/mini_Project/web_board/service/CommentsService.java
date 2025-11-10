package mini_Project.web_board.service;

import mini_Project.web_board.dto.CommentCreateReq;

public interface CommentsService {
    Long write(CommentCreateReq req);
}
