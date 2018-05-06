package com.longboAPIs.security;

import com.longboAPIs.entity.TableAuthority;
import com.longboAPIs.service.UserAuthoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    @Qualifier("UserDataSource")
    private DataSource userDataSource;

    @Autowired
    LoginSuccessHandle loginSuccessHandle;

    @Autowired
    UserAuthoritiesService userAuthoritiesService;


    @Override
    protected void configure(HttpSecurity http) throws Exception{

        //从api表中获取表名和权限
        List<TableAuthority> authorities = userAuthoritiesService.getAllTableAuthorities();
        for (TableAuthority authority: authorities){
            http.authorizeRequests()
                    .antMatchers("/jlzx/"+authority.getTable_name()+"/*/*").hasAuthority(authority.getTable_authority());
        }

        http.authorizeRequests()
                //admin用戶登录后界面
                .antMatchers("/admin/main").hasRole("ADMIN")
                .antMatchers("/userManagement/*").hasRole("ADMIN")
                .antMatchers("/userManagement/*/*").hasRole("ADMIN")
                .antMatchers("/userAuthoritiesManagement/*").hasRole("ADMIN")


                //非admin用户登录后界面
                .antMatchers("/instruction/*").authenticated()

                //A股信息表
//                .antMatchers("/jlzx/JL_BASE_1101/*/*").hasRole("TABLE_JL_BASE_1101")
//                .antMatchers("/jlzx/JL_BASE_1102/*/*").hasRole("TABLE_JL_BASE_1102")
//                .antMatchers("/jlzx/JL_BASE_New_2001/*/*").hasRole("TABLE_JL_BASE_New_2001")
//                .antMatchers("/jlzx/JL_BASE_2003/*/*").hasRole("TABLE_JL_BASE_2003")
//                .antMatchers("/jlzx/JL_BASE_2006/*/*").hasRole("TABLE_JL_BASE_2006")
//                .antMatchers("/jlzx/JL_BASE_2008/*/*").hasRole("TABLE_JL_BASE_2008")
//                .antMatchers("/jlzx/JL_BASE_2009/*/*").hasRole("TABLE_JL_BASE_2009")
//                .antMatchers("/jlzx/JL_BASE_2010/*/*").hasRole("TABLE_JL_BASE_2010")
//                .antMatchers("/jlzx/JL_BASE_2011/*/*").hasRole("TABLE_JL_BASE_2011")
//                .antMatchers("/jlzx/JL_BASE_2014/*/*").hasRole("TABLE_JL_BASE_2014")
//                .antMatchers("/jlzx/JL_BASE_2015/*/*").hasRole("TABLE_JL_BASE_2015")
//                .antMatchers("/jlzx/JL_BASE_2016/*/*").hasRole("TABLE_JL_BASE_2016")
//
//                .antMatchers("/jlzx/JL_BASE_2101/*/*").hasRole("JL_BASE_2101")
//                .antMatchers("/jlzx/JL_BASE_2102/*/*").hasRole("JL_BASE_2102")
//                .antMatchers("/jlzx/JL_BASE_2103/*/*").hasRole("JL_BASE_2103")
//                .antMatchers("/jlzx/JL_BASE_2104/*/*").hasRole("JL_BASE_2104")
//                .antMatchers("/jlzx/JL_BASE_2105/*/*").hasRole("JL_BASE_2105")
//                .antMatchers("/jlzx/JL_BASE_2106/*/*").hasRole("JL_BASE_2106")
//                .antMatchers("/jlzx/JL_BASE_2037/*/*").hasRole("JL_BASE_2037")
//                .antMatchers("/jlzx/JL_BASE_2121/*/*").hasRole("JL_BASE_2121")
//
//
//                .antMatchers("/jlzx/JL_BASE_2107/*/*").hasRole("TABLE_JL_BASE_2107")
//                .antMatchers("/jlzx/JL_BASE_2108/*/*").hasRole("TABLE_JL_BASE_2108")
//                .antMatchers("/jlzx/JL_BASE_2109/*/*").hasRole("TABLE_JL_BASE_2109")
//                .antMatchers("/jlzx/JL_BASE_2111/*/*").hasRole("TABLE_JL_BASE_2111")
//                .antMatchers("/jlzx/JL_BASE_2123/*/*").hasRole("TABLE_JL_BASE_2123")
//                .antMatchers("/jlzx/jl_ext_3001/*/*").hasRole("TABLE_jl_ext_3001")
//                .antMatchers("/jlzx/jl_ext_3005/*/*").hasRole("TABLE_jl_ext_3005")
//                .antMatchers("/jlzx/JL_EXT_3006/*/*").hasRole("TABLE_JL_EXT_3006")
//                .antMatchers("/jlzx/JL_EXT_3003_local/*/*").hasRole("TABLE_JL_EXT_3003_local")
//                .antMatchers("/jlzx/JL_EXT_3002_local_zq/*/*").hasRole("TABLE_JL_EXT_3002_local_zq")
//                .antMatchers("/jlzx/JL_EXT_4001/*/*").hasRole("TABLE_JL_EXT_4001")
//                .antMatchers("/jlzx/JL_EXT_4002/*/*").hasRole("TABLE_JL_EXT_4002")
//                .antMatchers("/jlzx/JL_EXT_4003/*/*").hasRole("TABLE_JL_EXT_4003")
//                .antMatchers("/jlzx/JL_BASE_3504/*/*").hasRole("TABLE_JL_BASE_3504")
//                .antMatchers("/jlzx/JL_BASE_3502/*/*").hasRole("TABLE_JL_BASE_3502")
//                .antMatchers("/jlzx/JL_BASE_3503/*/*").hasRole("TABLE_JL_BASE_3503")
//                .antMatchers("/jlzx/JL_DATA_3201/*/*").hasRole("TABLE_JL_DATA_3201")
//                .antMatchers("/jlzx/JL_DATA_3202/*/*").hasRole("TABLE_JL_DATA_3202")
//                .antMatchers("/jlzx/JL_DATA_3203/*/*").hasRole("TABLE_JL_DATA_3203")
//                .antMatchers("/jlzx/JL_BASE_7105/*/*").hasRole("TABLE_JL_BASE_7105")
//                .antMatchers("/jlzx/JL_BASE_9101/*/*").hasRole("TABLE_JL_BASE_9101" )
//                .antMatchers("/jlzx/JL_BASE_9102/*/*").hasRole("TABLE_JL_BASE_9102")
//                .antMatchers("/jlzx/JL_BASE_9103/*/*").hasRole("TABLE_JL_BASE_9103")
//
//                //港股信息表
//                .antMatchers("/jlzx/hk_main/*/*").hasRole("TABLE_hk_main")
//                .antMatchers("/jlzx/hk_security/*/*").hasRole("TABLE_hk_security")
//                .antMatchers("/jlzx/hk_gbjg/*/*").hasRole("TABLE_hk_gbjg")
//                .antMatchers("/jlzx/hk_hbst/*/*").hasRole("TABLE_hk_hbst")
//                .antMatchers("/jlzx/hk_hdiv/*/*").hasRole("TABLE_hk_hdiv")
//                .antMatchers("/jlzx/hk_hstk1/*/*").hasRole("TABLE_hk_hstk1")
//                .antMatchers("/jlzx/hk_lspecialnotice/*/*").hasRole("TABLE_hk_lspecialnotice")
//                .antMatchers("/jlzx/hk_ShareIPO/*/*").hasRole("TABLE_hk_ShareIPO")
//                .antMatchers("/jlzx/hk_lannouncement/*/*").hasRole("TABLE_hk_lannouncement")
//                .antMatchers("/jlzx/hk_lannouncement_nr/*/*").hasRole("TABLE_hk_lannouncement_nr")
//                .antMatchers("/jlzx/hk_news/*/*").hasRole("TABLE_hk_news")
//                .antMatchers("/jlzx/hk_news_rela/*/*").hasRole("TABLE_hk_news_rela")
//                .antMatchers("/jlzx/hk_daydata/*/*").hasRole("TABLE_hk_daydata")
//                .antMatchers("/jlzx/hk_company_1006/*/*").hasRole("TABLE_hk_company_1006")
//                .antMatchers("/jlzx/hk_company_1007/*/*").hasRole("TABLE_hk_company_1007")
//                .antMatchers("/jlzx/hk_balancesheet/*/*").hasRole("TABLE_hk_balancesheet")
//                .antMatchers("/jlzx/hk_cashflowstatement/*/*").hasRole("TABLE_hk_cashflowstatement")
//                .antMatchers("/jlzx/hk_incomestatement/*/*").hasRole("TABLE_hk_incomestatement")
//                .antMatchers("/jlzx/hk_bx_balancesheet/*/*").hasRole("TABLE_hk_bx_balancesheet")
//                .antMatchers("/jlzx/hk_bx_cashflowstatement/*/*").hasRole("TABLE_hk_bx_cashflowstatement")
//                .antMatchers("/jlzx/hk_bx_incomestatement/*/*").hasRole("TABLE_hk_bx_incomestatement")
//                .antMatchers("/jlzx/hk_tz_balancesheet/*/*").hasRole("TABLE_hk_tz_balancesheet")
//                .antMatchers("/jlzx/hk_tz_cashflowstatement/*/*").hasRole("TABLE_hk_tz_cashflowstatement")
//                .antMatchers("/jlzx/hk_tz_incomestatement/*/*").hasRole("TABLE_hk_tz_incomestatement")
//                .antMatchers("/jlzx/hk_cwbl/*/*").hasRole("TABLE_hk_cwbl")
//                .antMatchers("/jlzx/hk_cwbl2/*/*").hasRole("TABLE_hk_cwbl2")
//
//                //美股信息表
//                .antMatchers("/jlzx/ccs_company/*/*").hasRole("TABLE_ccs_company")
//                .antMatchers("/jlzx/ccs_stock/*/*").hasRole("TABLE_ccs_stock")
//                .antMatchers("/jlzx/ccs_issue/*/*").hasRole("TABLE_ccs_issue")
//                .antMatchers("/jlzx/ccs_hbst/*/*").hasRole("TABLE_ccs_hbst")
//                .antMatchers("/jlzx/ccs_gbjg/*/*").hasRole("TABLE_ccs_gbjg")
//                .antMatchers("/jlzx/ccs_dividend/*/*").hasRole("TABLE_ccs_dividend")
//                .antMatchers("/jlzx/ccs_plate/*/*").hasRole("TABLE_ccs_plate")
//                .antMatchers("/jlzx/ccs_codeplate/*/*").hasRole("TABLE_ccs_codeplate")
//                .antMatchers("/jlzx/ccs_senior/*/*").hasRole("TABLE_ccs_senior")
//                .antMatchers("/jlzx/ccs_incomestatement/*/*").hasRole("TABLE_ccs_incomestatement")
//                .antMatchers("/jlzx/css_balancesheet/*/*").hasRole("TABLE_css_balancesheet")
//                .antMatchers("/jlzx/ccs_cashflowstatement/*/*").hasRole("TABLE_ccs_cashflowstatement")
//                .antMatchers("/jlzx/ccs_Quotation/*/*").hasRole("ccs_Quotation")
//                .antMatchers("/jlzx/ccs_AnticipatedShareQuotation/*/*").hasRole("TABLE_ccs_AnticipatedShareQuotation")
//                .antMatchers("/jlzx/ccs_Announcement/*/*").hasRole("TABLE_ccs_Announcement")
//                .antMatchers("/jlzx/ccs_News/*/*").hasRole("TABLE_ccs_News")
//                .antMatchers("/jlzx/ccs_NewsRela/*/*").hasRole("TABLE_ccs_NewsRela")
//                .antMatchers("/jlzx/ccs_report/*/*").hasRole("TABLE_ccs_report")


                //静态资源登陆即可访问
                .antMatchers("/vendor/*").authenticated()
                .antMatchers("/vendor/*/*").authenticated()
                .antMatchers("/vendor/*/*/*").authenticated()
                .antMatchers("/vendor/*/*/*/*").authenticated()


                .anyRequest().denyAll()
                .and()
        .formLogin() //默认管理员登录界面
                .loginPage("/login").permitAll()
                .successHandler(loginSuccessHandle)
                .loginProcessingUrl("/login")
                .and()
        .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
        .and()
        .headers().frameOptions().disable(); //默认情况下不能在frame里使用url请求，disable掉frameOption即可

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
          .jdbcAuthentication()
            .dataSource(userDataSource)
                //数据库中使用时间来限定用户权限，如果权限已经过期，则无法使用
                .usersByUsernameQuery("select username,password,enabled from users where username = ? and enableduntil >='"+ LocalDate.now().toString()+"'")
                //开启group功能！！！
                .groupAuthoritiesByUsername("select g.id, g.group_name, ga.authority from groups g, group_members gm, group_authorities ga where gm.username = ? and g.id = ga.group_id and g.id = gm.group_id");
    }

}
