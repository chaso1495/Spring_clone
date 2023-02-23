package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MemberSaveServlet.service"); // request에 kim과 20이 담겨 있음
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age")); // 추출

        Member member = new Member(username, age); // 객체 생성
        System.out.println("member = " + member);
        memberRepository.save(member); // 저장

        response.setContentType("text/html"); // 응답
        response.setCharacterEncoding("utf-8");
        PrintWriter w = response.getWriter();
        w.write("<html>\n" + // 저장된 결과를 html 형식으로 화면에 출력
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" + "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                "    <li>id=" + member.getId() + "</li>\n" +
                "    <li>username=" + member.getUsername() + "</li>\n" + " <li>age=" + member.getAge() + "</li>\n" + "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" + "</body>\n" +
                "</html>");
        //        1. 파라미터를 조회해서 Member 객체를 만든다.
        //        2. Member 객체를 MemberRepository를 통해서 저장한다.
        //        3. Member 객체를 사용해서 결과 화면용 HTML을 동적으로 만들어서 응답한다.
    }
}
