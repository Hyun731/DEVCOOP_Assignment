import controller.PostController;
import controller.UserController;
import domain.PostDomain;
import repository.MemoryPostRepository;
import repository.MemoryUserRepository;
import service.PostService;
import service.UserService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PostService postService = new PostService(new MemoryPostRepository());
        PostController postController = new PostController(postService);
        UserService userService = new UserService(new MemoryUserRepository());
        UserController userController = new UserController(userService);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printAuthMenu();
            String command = scanner.nextLine();

            try {
                switch (command) {
                    case "1":
                        signUp(scanner, userController);
                        break;
                    case "2":
                        String loginUserId = login(scanner, userController);
                        if (loginUserId != null) {
                            runBoardMenu(scanner, postController, loginUserId);
                        }
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("잘못된 메뉴입니다. 다시 입력하세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자 형식으로 입력해 주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println("에러");
            }
        }
    }

    private static void printAuthMenu() {
        System.out.println("1. 회원가입");
        System.out.println("2. 로그인");
        System.out.println("0. 종료");
        System.out.print("메뉴 선택: ");
    }

    private static void signUp(Scanner scanner, UserController userController) {
        System.out.print("아이디: ");
        String id = scanner.nextLine();
        System.out.print("비밀번호: ");
        String pw = scanner.nextLine();
        userController.signUp(id, pw);
        System.out.println("회원가입 완료.");
    }

    private static String login(Scanner scanner, UserController userController) {
        System.out.print("아이디: ");
        String id = scanner.nextLine();
        System.out.print("비밀번호: ");
        String pw = scanner.nextLine();
        boolean loggedIn = userController.login(id, pw);
        if (!loggedIn) {
            System.out.println("존재 하지 않는 계정입니다.");
            return null;
        }
        System.out.println("로그인 성공.");
        return id;
    }

    private static void runBoardMenu(Scanner scanner, PostController postController, String loginUserId) {
        while (true) {
            printBoardMenu();
            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    createPost(scanner, postController, loginUserId);
                    break;
                case "2":
                    printPosts(postController.list());
                    break;
                case "3":
                    readPost(scanner, postController);
                    break;
                case "4":
                    updatePost(scanner, postController);
                    break;
                case "5":
                    deletePost(scanner, postController);
                    break;
                case "6":
                    System.out.println("로그아웃 되었습니다.");
                    return;
                default:
                    System.out.println("잘못된 메뉴입니다. 다시 입력하세요.");
            }
        }
    }

    private static void printBoardMenu() {
        System.out.println("1. 글 등록");
        System.out.println("2. 글 목록");
        System.out.println("3. 글 상세");
        System.out.println("4. 글 수정");
        System.out.println("5. 글 삭제");
        System.out.println("6. 로그아웃");
        System.out.print("메뉴 선택: ");
    }

    private static void createPost(Scanner scanner, PostController postController, String loginUserId) {
        System.out.print("제목: ");
        String title = scanner.nextLine();
        System.out.print("내용: ");
        String content = scanner.nextLine();

        PostDomain post = postController.create(title, content, loginUserId);
        System.out.println("등록 완료. 게시글 ID: " + post.getId());
    }

    private static void printPosts(List<PostDomain> posts) {
        if (posts.isEmpty()) {
            System.out.println("등록된 게시글이 없습니다.");
            return;
        }

        System.out.println("\n게시글 목록");
        for (PostDomain post : posts) {
            System.out.println(post.getId() + " - " + post.getTitle() + " - " + post.getAuthor());
        }
    }

    private static void readPost(Scanner scanner, PostController postController) {
        System.out.print("조회할 ID: ");
        long id = Long.parseLong(scanner.nextLine());

        postController.findById(id).ifPresentOrElse(
                post -> {
                    System.out.println("\n게시글 상세");
                    System.out.println("ID: " + post.getId());
                    System.out.println("작성자: " + post.getAuthor());
                    System.out.println("제목: " + post.getTitle());
                    System.out.println("내용: " + post.getContent());
                },
                () -> System.out.println("해당 ID의 게시글이 없습니다.")
        );
    }

    private static void updatePost(Scanner scanner, PostController postController) {
        System.out.print("수정할 ID: ");
        long id = Long.parseLong(scanner.nextLine());
        System.out.print("새 제목: ");
        String title = scanner.nextLine();
        System.out.print("새 내용: ");
        String content = scanner.nextLine();

        boolean updated = postController.update(id, title, content);
        System.out.println(updated ? "수정 완료." : "존재 하지 않는 게시글입니다.");
    }

    private static void deletePost(Scanner scanner, PostController postController) {
        System.out.print("삭제할 ID: ");
        long id = Long.parseLong(scanner.nextLine());

        boolean deleted = postController.delete(id);
        System.out.println(deleted ? "삭제 완료." : "존재 하지 않는 게시글입니다.");
    }
}
