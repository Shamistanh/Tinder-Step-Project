//package filters;
//
//import service.Checker;
//import service.MyCookie;
//
//import javax.servlet.*;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class LoginFilter implements Filter {
//
//    private boolean isHttp(ServletRequest req) {
//        return req instanceof HttpServletRequest;
//    }
//
//    public boolean isCookieOk(HttpServletRequest req) {
//        String user_name  = MyCookie.get("my_name", req);
//        String password = MyCookie.get("my_pwd", req);
//        return Checker.check(user_name, password);
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {}
//
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        if (isHttp(request) && isCookieOk((HttpServletRequest)request)) {
//            chain.doFilter(request, response);
//        }
//        else ((HttpServletResponse)response).sendRedirect("/login");
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
