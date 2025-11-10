package mini_Project.web_board.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class CategoryRes {
    private Long id;
    private String name;
    private Long main_category_id;
}
