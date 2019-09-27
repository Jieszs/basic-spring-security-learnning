package com.customlogincaptcha.demo.config;


import com.customlogincaptcha.demo.filter.CaptchaFilter;


import com.customlogincaptcha.demo.handler.MyAuthenticationFailureHandler;
import com.customlogincaptcha.demo.handler.MyAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;
    @Resource
    private MyAuthenticationFailureHandler authenticationFailureHandler;
    @Resource
    private CaptchaFilter captchaFilter;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() // 表单登录
                .loginPage("/auth") //指定了跳转到登录页面的请求URL
                .loginProcessingUrl("/login")// 对应登录页面form表单的action="/login"
                .successHandler(authenticationSuccessHandler)//登陆成功处理
                .failureHandler(authenticationFailureHandler)//登陆失败处理
                .and()
                .authorizeRequests() // 授权配置
                //表示跳转到登录页面的请求不被拦截，否则会进入无限循环。登录跳转 URL 无需认证
                .antMatchers("/auth", "/captcha", "/login.html").permitAll()//验证码
                .anyRequest()  // 所有请求
                .authenticated() // 都需要认证
                .and().csrf().disable()//关闭CSRF攻击防御
                .addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class);//在账号密码过滤器之前先过滤验证码
    }
}
