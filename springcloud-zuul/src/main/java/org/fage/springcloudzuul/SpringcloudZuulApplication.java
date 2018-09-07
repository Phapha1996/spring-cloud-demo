package org.fage.springcloudzuul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

/**
 *  zuul 详解
 */
@SpringCloudApplication
@EnableZuulProxy
public class SpringcloudZuulApplication {
    static final Logger logger = LoggerFactory.getLogger(SpringcloudZuulApplication.class);

	public static void main(String[] args) {
        SpringApplication.run(SpringcloudZuulApplication.class, args);
        logger.info("\n                              _ooOoo_\n                             o8888888o\n                             88\" . \"88\n                             (| -_- |)\n                             O\\  =  /O\n                          ____/`---'\\____\n                        .'  \\\\|     |//  `.\n                       /  \\\\|||  :  |||//  \\\n                      /  _||||| -:- |||||-  \\\n                      |   | \\\\\\  -  /// |   |\n                      | \\_|  ''\\---/''  |   |\n                      \\  .-\\__  `-`  ___/-. /\n                    ___`. .'  /--.--\\  `. . __\n                 .\"\" '<  `.___\\_<|>_/___.'  >'\"\".\n                | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |\n                \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /\n           ======`-.____`-.___\\_____/___.-`____.-'======\n                              `=---='\n           ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n                      佛祖保佑        永无BUG\n\n **************************************************************\n *                                                            *\n *   .=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.       *\n *    |                     ______                     |      *\n *    |                  .-\"      \"-.                  |      *\n *    |                 /            \\                 |      *\n *    |     _          |              |          _     |      *\n *    |    ( \\         |,  .-.  .-.  ,|         / )    |      *\n *    |     > \"=._     | )(__/  \\__)( |     _.=\" <     |      *\n *    |    (_/\"=._\"=._ |/     /\\     \\| _.=\"_.=\"\\_)    |      *\n *    |           \"=._\"(_     ^^     _)\"_.=\"           |      *\n *    |               \"=\\__|IIIIII|__/=\"               |      *\n *    |              _.=\"| \\IIIIII/ |\"=._              |      *\n *    |    _     _.=\"_.=\"\\          /\"=._\"=._     _    |      *\n *    |   ( \\_.=\"_.=\"     `--------`     \"=._\"=._/ )   |      *\n *    |    > _.=\"                            \"=._ <    |      *\n *    |   (_/                                    \\_)   |      *\n *    |                                                |      *\n *    '-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-='      *\n *                                                            *\n *           LASCIATE OGNI SPERANZA, VOI CH'ENTRATE           *\n **************************************************************\n//                       .::::.\n//                     .::::::::.\n//                    :::::::::::\n//                 ..:::::::::::'\n//              '::::::::::::'\n//                .::::::::::\n//           '::::::::::::::..\n//                ..::::::::::::.\n//              ``::::::::::::::::\n//               ::::``:::::::::'        .:::.\n//              ::::'   ':::::'       .::::::::.\n//            .::::'      ::::     .:::::::'::::.\n//           .:::'       :::::  .:::::::::' ':::::.\n//          .::'        :::::.:::::::::'      ':::::.\n//         .::'         ::::::::::::::'         ``::::.\n//     ...:::           ::::::::::::'              ``::.\n//    ```` ':.          ':::::::::'                  ::::..\n//                       '.:::::'                    ':'````..\n//");
    }

    /**
     * 创建自定义路由映射规则：
     * serviceA-v1   可以映射到  v1/serviceA
     * serviceB-v1   可以映射到  v1/serviceB
     * @return
     */
    @Bean
    public PatternServiceRouteMapper serviceRouteMapper(){
        //应用名称
	    String serviceId = "(?<name>^.+)-(?<version>v.+$)";
	    //上面应用名称映射的访问路径
	    String routeMapping = "${version}/${name}";
	    return new PatternServiceRouteMapper(serviceId, routeMapping);
    }
}
