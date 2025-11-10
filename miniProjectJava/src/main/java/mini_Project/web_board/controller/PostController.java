package mini_Project.web_board.controller;

import mini_Project.web_board.dto.PostFormDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class PostController {
    @GetMapping("/write") // 글쓰기
    public String showWriteForm(Model model) {
        PostFormDto postFormDto = new PostFormDto();
        model.addAttribute("postForm", postFormDto);
        return "write";
    }
    @PostMapping("/write")
    public String handleSubmitWriteForm(@ModelAttribute("postForm") PostFormDto postFormDto) {
        System.out.println("--- 폼 데이터가 컨트롤러에 도착했습니다 ---");
        System.out.println("제목 (title): " + postFormDto.getTitle());
        System.out.println("내용 (content): " + postFormDto.getContent());
        System.out.println("댓글 허용 (allowComments): " + postFormDto.isAllowComments());
        System.out.println("기타 (extraOption): " + postFormDto.isExtraOption());
        System.out.println("---------------------------------------");
        return "redirect:/board/list"; // (게시판 목록 페이지의 URL로 변경하세요)
    }

    @GetMapping("/list")
    public String showBoardList(Model model) {
        return "postlist";
    }


}