/**
 *    Copyright 2009-2019 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.plugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Clinton Begin
 */
public class InterceptorChain {

  private final List<Interceptor> interceptors = new ArrayList<>();

  /**
   * pluginAll 如何保证最后一次遍历的结果就是目标值 ？
   *
   * 注意它是把interceptor.plugin方法处理的结果又赋值给target了
   * 而且在interceptor.plugin方法内部，如果没有匹配上正确的拦截器
   * 会把之前传入的target不做任何处理的返回，通过这种方式就能保证最后一次
   * 处理的结果就是目标值。
   *
   */
  public Object pluginAll(Object target) {
    for (Interceptor interceptor : interceptors) {
      // 如果target是实现相关拦截接口的类，经过interceptor.plugin方法处理后，返回的其实是target的代理
      target = interceptor.plugin(target);
    }
    return target;
  }

  public void addInterceptor(Interceptor interceptor) {
    interceptors.add(interceptor);
  }

  public List<Interceptor> getInterceptors() {
    return Collections.unmodifiableList(interceptors);
  }

}
