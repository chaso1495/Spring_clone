package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet { // API body에 데이터를 직접 담아서 요청
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream(); // body의 내용을 바이트코드로 바로 얻음
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // 바이트코드를 스트링으로 변환
        System.out.println("messageBody = " + messageBody);
        response.getWriter().write("ok");
        // 요즘은 이런 방식보다는 JSON으로 데이터를 주로 주고받는다.

    }
}