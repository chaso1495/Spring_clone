package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet { // 프론트 컨트롤러 패턴
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() { // 생성될 때 패턴에 맞추어 컨트롤러 객체들이 생성됨
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1()); // key, value
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        String requestURI = request.getRequestURI(); // 요청으로부터 url 확보
        ControllerV1 controller = controllerMap.get(requestURI); // key url에 해당하는 컨트롤러 객체를 받아옴
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404 띄우기
            return;
        }
        controller.process(request, response); // 인터페이스의 다형성 활용하여 각 컨트롤러마다 다른 행동 작용
    }
}

