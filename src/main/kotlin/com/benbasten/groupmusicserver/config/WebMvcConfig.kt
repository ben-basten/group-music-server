package com.benbasten.groupmusicserver.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebMvcConfig : WebMvcConfigurer {

    // By default, the async controller endpoints create a new thread for every request.
    // In the case of downloading the music, this could be a detriment to performance at scale.
    // This configures the amount of threads that can be open at once for async requests.
    @Bean
    fun mvcTaskExecutor(): ThreadPoolTaskExecutor {
        val taskExecutor = ThreadPoolTaskExecutor()
        taskExecutor.corePoolSize = 5 // number of threads to keep alive without timing out
        taskExecutor.maxPoolSize = 10 // max number of threads that can ever be created
        return taskExecutor
    }

    override fun configureAsyncSupport(configurer: AsyncSupportConfigurer) {
        configurer.setTaskExecutor(mvcTaskExecutor()) // using the task executor that was just configured
    }
}