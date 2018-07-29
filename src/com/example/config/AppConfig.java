package com.example.config;

import java.util.List;
import java.util.Locale;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.example")
@PropertySource("classpath:datasource.properties")
@MapperScan("com.example.mapper")
public class AppConfig extends WebMvcConfigurationSupport{

	@Autowired
	private Environment env;
	
	//静态资源的映射
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/statics/**").addResourceLocations("/WEB-INF/statics/");
    }
	
	@Bean
	public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		jsonConverter.setObjectMapper(objectMapper);
		return jsonConverter;
	}
	//支持json格式
	@Override
	protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(customJackson2HttpMessageConverter());
		super.configureMessageConverters(converters);
	}
	
	//视图模版解析
	@Bean(name="viewResolver")
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setExposeContextBeansAsAttributes(true);
        return viewResolver;
    }
	
	//定义消息源
	@Bean(name="messageSource")
    public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/message");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(0);
        return messageSource;
    }
	
	@Override
	public Validator getValidator() {
	    LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
	    validator.setValidationMessageSource(messageSource());
	    return validator;
	}
	
	@Bean
   public LocaleResolver localeResolver() {
      CookieLocaleResolver localeResolver = new CookieLocaleResolver();
      return localeResolver;
   }
	
	//拦截器， 切换多国语言, 没有效果
//	@Override
//	protected void addInterceptors(InterceptorRegistry registry) {
//		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
//	    lci.setParamName("sitelang");
//		registry.addInterceptor(lci);
//	}
	
	//拦截器， 切换多国语言, 
	@Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping requestMappingHandlerMapping = super.requestMappingHandlerMapping();
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
	    lci.setParamName("sitelang");
        requestMappingHandlerMapping.setInterceptors(new Object[] {lci});
        return requestMappingHandlerMapping;
    }

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	CharacterEncodingFilter characterEncodingFilter() {
	  CharacterEncodingFilter filter = new CharacterEncodingFilter();
	  filter.setEncoding("UTF-8");
	  filter.setForceEncoding(true);
	  return filter;
	}
	
	
	//数据源
	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //MySQL database we are using
        dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
        dataSource.setUrl(env.getProperty("jdbc.url"));//change url
        dataSource.setUsername(env.getProperty("jdbc.username"));//change userid
        dataSource.setPassword(env.getProperty("jdbc.password"));//change pwd
        
		return dataSource;
	}
	
	//mybatis setting
	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean fac =  new SqlSessionFactoryBean();
		fac.setDataSource(dataSource());
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		fac.setConfigLocation( resolver.getResource("classpath:mybatis-config.xml") );
		return fac.getObject();
	}
    
}
