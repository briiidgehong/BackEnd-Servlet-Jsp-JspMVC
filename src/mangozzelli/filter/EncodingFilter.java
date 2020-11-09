package mangozzelli.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

// filter 적용 방법 -> servlet 적용 방법과 마찬가지로 web.xml 의 mapping 을 이용하거나
// 어노테이션을 이용하면 된다.
// /* : 모든 url 에 대해서 filter 적용
@WebFilter("/*")
public class EncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        //해당 filter 를 거치는 모든 문서에 대해 encoding 적용
        servletRequest.setCharacterEncoding("UTF-8");
        System.out.println("before filter");
        //흐름을 넘겨주는 역할 -> 다른 필터로 넘길수도, 원래 흘러가던대로 넘길수도 있다.
        //원래 흘러가던대로 넘긴다.
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("after filter");
    }

    @Override
    public void destroy() {

    }
}
