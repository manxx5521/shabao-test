<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../../context/head.jsp"%>
<%@include file="../common.jsp"%>
<title>沙包娱乐 - 用户添加</title>
<cs:resource type="all" value="jquery,bootstrap,system,jstree,bootbox" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-4">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>H+ 模态窗口</h5>

                    </div>
                    <div class="ibox-content">
                        <p>
                            创建自定义的H+模态窗口可通过添加<code>.inmodal</code>类来实现。
                        </p>
                        <div class="text-center">
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                                打开示例窗口
                            </button>
                        </div>
                        <small class="font-bold">

                        </small></div><small class="font-bold">
                    </small></div><small class="font-bold">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>大小设置</h5>

                        </div>
                        <div class="ibox-content">
                            <p>
                                模态窗口提供两种大小尺寸，可以通过为模态窗口的<code>.modal-dialog</code>添加类来实现
                            </p>

                            <div class="text-center">
                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal5">
                                    大模态窗口
                                </button>
                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal6">
                                    小模态窗口
                                </button>
                            </div>
                            <small class="font-bold">
                            

                        </small></div><small class="font-bold">
                    </small></div><small class="font-bold">
                </small></small></div><small class="font-bold"><small class="font-bold">
                <div class="col-sm-8">
                    <div class="ibox ">
                        <div class="ibox-title">
                            <h5>动画窗口</h5>

                        </div>
                        <div class="ibox-content">
                            <p>
                                您可以通过为模态窗口的<code>.modal-content</code>添加类来实现动画效果
                            </p>

                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal2">
                                沿X轴转动
                            </button>
                            <small class="font-bold">

                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal4">
                                基本动画
                            </button>
                            <small>

                        </small></small></div><small class="font-bold"><small>
                    </small></small></div><small class="font-bold"><small>
                    <div class="ibox ">
                        <div class="ibox-title">
                            <h5>设置选项</h5>

                        </div>
                        <div class="ibox-content">
                            <p>
                                可以通过数据绑定或者Javascript来实现模态窗口的相关功能，如果使用数据绑定，可以为元素添加<code>data-</code>，如 <code>data-backdrop=""</code>。
                            </p>

                            <div class="table-responsive">
                                <table class="table table-bordered table-striped">
                                    <thead>
                                    <tr>
                                        <th style="width: 100px;">名称</th>
                                        <th style="width: 50px;">类型</th>
                                        <th style="width: 50px;">默认值</th>
                                        <th>说明</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>backdrop</td>
                                        <td>boolean 或 string <code>'static'</code></td>
                                        <td>true</td>
                                        <td>遮罩层，或使用<code>'static'</code>指定遮罩层与关闭模态窗口不关联</td>
                                    </tr>
                                    <tr>
                                        <td>keyboard</td>
                                        <td>boolean</td>
                                        <td>true</td>
                                        <td>按Esc键时退出模态窗口</td>
                                    </tr>
                                    <tr>
                                        <td>show</td>
                                        <td>boolean</td>
                                        <td>true</td>
                                        <td>初始化完成后显示模态窗口</td>
                                    </tr>
                                    <tr>
                                        <td>remote</td>
                                        <td>path</td>
                                        <td>false</td>
                                        <td>
                                            <p><strong class="text-danger">推荐使用数据绑定方式，或使用<a href="http://api.jquery.com/load/">jQuery.load</a></strong></p><strong class="text-danger">
                                            <p>远程URL示例：</p>
                                            <div class="highlight"><pre><code class="language-html" data-lang="html"><span class="nt">&lt;a</span> <span class="na">data-toggle=</span><span class="s">"modal"</span> <span class="na">href=</span><span class="s">"remote.html"</span> <span class="na">data-target=</span><span class="s">"#modal"</span><span class="nt">&gt;</span>Click me<span class="nt">&lt;/a&gt;</span></code></pre></div>
                                        </strong></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </small></small></div><small class="font-bold"><small>
            </small></small></small></small></div><small class="font-bold"><small class="font-bold"><small class="font-bold"><small>
    </small></small></small></small></div><small class="font-bold"><small class="font-bold"><small class="font-bold"><small>
    <script src="./H+ 后台主题UI框架 - 模态窗口_files/jquery.min.js"></script>
    <script src="./H+ 后台主题UI框架 - 模态窗口_files/bootstrap.min.js"></script>
    <script src="./H+ 后台主题UI框架 - 模态窗口_files/content.min.js"></script>
    <script type="text/javascript" src="./H+ 后台主题UI框架 - 模态窗口_files/stats" charset="UTF-8"></script>



</small></small></small></small>
<div class="modal inmodal" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content animated bounceInRight">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<i class="fa fa-laptop modal-icon"></i>
					<h4 class="modal-title">窗口标题</h4>
					<small class="font-bold">这里可以显示副标题。 </small>
				</div>
				<small class="font-bold">
					<div class="modal-body">
						<p>
							<strong>H+</strong>
							是一个完全响应式，基于Bootstrap3.3.6最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术，她提供了诸多的强大的可以重新组合的UI组件，并集成了最新的jQuery版本(v2.1.1)，当然，也集成了很多功能强大，用途广泛的jQuery插件，她可以用于所有的Web应用程序，如网站管理后台，网站会员中心，CMS，CRM，OA等等，当然，您也可以对她进行深度定制，以做出更强系统。
						</p>
						<div class="form-group">
							<label>Email</label> <input type="email" placeholder="请输入您的Email"
								class="form-control">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary">保存</button>
					</div>
				</small>
			</div>
			<small class="font-bold"> </small>
		</div>
		<small class="font-bold">
        </small>
  </div><div class="modal inmodal fade" id="myModal5" tabindex="-1" role="dialog" aria-hidden="true">
                                <div class="modal-dialog modal-lg">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                                            <h4 class="modal-title">窗口标题</h4>
                                            <small class="font-bold">这里可以显示副标题。
                                        </small></div><small class="font-bold">
                                        <div class="modal-body">
                                            <p><strong>H+</strong> 是一个完全响应式，基于Bootstrap3.3.6最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术，她提供了诸多的强大的可以重新组合的UI组件，并集成了最新的jQuery版本(v2.1.1)，当然，也集成了很多功能强大，用途广泛的jQuery插件，她可以用于所有的Web应用程序，如网站管理后台，网站会员中心，CMS，CRM，OA等等，当然，您也可以对她进行深度定制，以做出更强系统。</p>
                                        </div>

                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                            <button type="button" class="btn btn-primary">保存</button>
                                        </div>
                                    </small></div><small class="font-bold">
                                </small></div><small class="font-bold">
                            </small></div><div class="modal inmodal fade" id="myModal6" tabindex="-1" role="dialog" aria-hidden="true">
                                <div class="modal-dialog modal-sm">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                                            <h4 class="modal-title">窗口标题</h4>
                                        </div>
                                        <div class="modal-body">
                                            <p><strong>H+</strong> 是一个完全响应式，基于Bootstrap3.3.6最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术，她提供了诸多的强大的可以重新组合的UI组件，并集成了最新的jQuery版本(v2.1.1)，当然，也集成了很多功能强大，用途广泛的jQuery插件，她可以用于所有的Web应用程序，如网站管理后台，网站会员中心，CMS，CRM，OA等等，当然，您也可以对她进行深度定制，以做出更强系统。</p>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                            <button type="button" class="btn btn-primary">保存</button>
                                        </div>
                                    </div>
                                </div>
                            </div><div class="modal inmodal" id="myModal2" tabindex="-1" role="dialog" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content animated flipInY">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                                            <h4 class="modal-title">窗口标题</h4>
                                            <small class="font-bold">这里可以显示副标题。
                                        </small></div><small class="font-bold">
                                        <div class="modal-body">
                                            <p><strong>H+</strong> 是一个完全响应式，基于Bootstrap3.3.6最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术，她提供了诸多的强大的可以重新组合的UI组件，并集成了最新的jQuery版本(v2.1.1)，当然，也集成了很多功能强大，用途广泛的jQuery插件，她可以用于所有的Web应用程序，如网站管理后台，网站会员中心，CMS，CRM，OA等等，当然，您也可以对她进行深度定制，以做出更强系统。</p>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                            <button type="button" class="btn btn-primary">保存s</button>
                                        </div>
                                    </small></div><small class="font-bold">
                                </small></div><small class="font-bold">
                            </small></div><div class="modal inmodal" id="myModal4" tabindex="-1" role="dialog" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content animated fadeIn">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                                            <i class="fa fa-clock-o modal-icon"></i>
                                            <h4 class="modal-title">窗口标题</h4>
                                            <small>这里可以显示副标题。
                                        </small></div><small>
                                        <div class="modal-body">
                                            <p><strong>H+</strong> 是一个完全响应式，基于Bootstrap3.3.6最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术，她提供了诸多的强大的可以重新组合的UI组件，并集成了最新的jQuery版本(v2.1.1)，当然，也集成了很多功能强大，用途广泛的jQuery插件，她可以用于所有的Web应用程序，如网站管理后台，网站会员中心，CMS，CRM，OA等等，当然，您也可以对她进行深度定制，以做出更强系统。</p>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                            <button type="button" class="btn btn-primary">保存</button>
                                        </div>
                                    </small></div><small>
                                </small></div><small>
                            </small></div><div class="gohome"><a class="animated bounceInUp" href="http://www.zi-han.net/theme/hplus/index.html?v=4.0" title="返回首页"><i class="fa fa-home"></i></a></div></body></html>