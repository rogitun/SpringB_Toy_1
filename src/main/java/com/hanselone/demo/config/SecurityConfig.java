package com.hanselone.demo.config;

import com.hanselone.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration //config bean을 명시해주는 어노테이션
@EnableWebSecurity //spring security config를 할 클래스라는 걸 명시
@AllArgsConstructor
//@EnableGlobalMethodSecurity(prePostEnabled = true) => 권한 있는 유저만 접근 허용하도록
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        //암호화를 통해 db에 비밀번호를 저장
    }
    @Override
    public void configure(WebSecurity web) throws Exception{
//        web.ignoring().antMatchers("/**"); 경로에 포함된 파일의 요청은 인증 대상에서 제외시킨다. ,현재 css, js 등 스태틱 관련 파일 없으니 제외
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/**").permitAll();
        http.formLogin()
                .loginPage("/user/login") //로그엔 피이지
                .defaultSuccessUrl("/")//로그인 성공시 이동하는 페이지
                .usernameParameter("account")
                .permitAll();
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")) //로그아웃 경로
                .logoutSuccessUrl("/") //로그아웃 성공시 이동 경로
                .invalidateHttpSession(true); //로그아웃 성공 시 세션 제거
        http.exceptionHandling()
                .accessDeniedPage("/denied"); //권한 없는 사용자의 접근 시 이동 경로
    }

    @Override //AuthenticationManagerBuilder => 사용자 인증을 담당
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
        //비밀번호 체크하는 로직을 위해선 UserDetailService 인터페이스를 구현한 클래스가 필요하다.
    }
}
