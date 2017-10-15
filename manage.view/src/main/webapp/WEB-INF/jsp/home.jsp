<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="header.jsp"%>

<div id="main">
    <div class="container" id="daily">
        <div class="jscroll-inner">
            <div class="daily">

            <c:set value="" var="curDate"></c:set>

            <c:forEach items="${vos}" var="vo" varStatus="index">

               <c:if test="${curDate !=vo.news.createdDate}">
                    <h3 class="date">
                        <i class="fa icon-calendar"></i>
                        <span>头条资讯 &nbsp; ${ fn:replace(vo.news.createdDate,"\"" ,"" )}  </span>
                        <c:set var="curDate" value="${vo.news.createdDate}" ></c:set>
                    </h3>
               </c:if>

                <div class="posts">
                    <div class="post">
                        <div class="votebar">

                            <c:choose>
                                <c:when test="${vo.like >0}">
                                         <button class="click-like up pressed" data-id="${vo.news.id}" title="赞同"><i class="vote-arrow"></i>
                                             <span class="count">${vo.news.likeCount}</span></button>
                                </c:when>
                                <c:otherwise>
                                    <button class="click-like up" data-id="${vo.news.id}" title="赞同"><i class="vote-arrow"></i>
                                        <span class="count">${vo.news.likeCount}</span></button>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${vo.like < 0}">
                                    <button class="click-dislike down pressed" data-id="${vo.news.id}" title="反对"><i class="vote-arrow"></i></button>
                                </c:when>
                                <c:otherwise>
                                    <button class="click-dislike down" data-id="${vo.news.id}" title="反对"><i class="vote-arrow"></i></button>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="content" data-url="http://nowcoder.com/posts/5l3hjr">
                            <div>
                                <img class="content-img" src="${vo.news.image}" alt="">
                            </div>
                            <div class="content-main">
                                <h3 class="title">  <!--${vo.news.link}-->
                                    <a target="_blank" rel="external nofollow"
                                       href="/news/${vo.news.id}">${vo.news.title}</a>
                                </h3>
                                <div class="meta">
                                    ${vo.news.link}
                                    <span>
                                        <i class="fa icon-comment"></i> ${vo.news.commentCount}
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="user-info">
                            <div class="user-avatar">
                                <a href="/user/${vo.user.id}/"><img width="32" class="img-circle"
                                                                     src="${vo.user.headUrl}"></a>
                            </div>

                            <!--
                            <div class="info">
                                <h5>分享者</h5>

                                <a href="http://nowcoder.com/u/251205"><img width="48" class="img-circle" src="http://images.nowcoder.com/images/20141231/622873_1420036789276_622873_1420036771761_%E8%98%91%E8%8F%87.jpg@0e_200w_200h_0c_1i_1o_90Q_1x" alt="Thumb"></a>

                                <h4 class="m-b-xs">冰燕</h4>
                                <a class="btn btn-default btn-xs" href="http://nowcoder.com/signin"><i class="fa icon-eye"></i> 关注TA</a>
                            </div>
                            -->
                        </div>

                        <div class="subject-name">来自 <a href="/user/${vo.user.id}/">${vo.user.name}</a></div>
                    </div>

                    <!--
                    <div class="alert alert-warning subscribe-banner" role="alert">
                      《头条八卦》，每日 Top 3 通过邮件发送给你。      <a class="btn btn-info btn-sm pull-right" href="http://nowcoder.com/account/settings">立即订阅</a>
                    </div>
                    -->
                </div>

            </c:forEach>

            </div>
         </div>
    </div>

</div>

<c:if test="${pop != null}">
    <script>
        window.loginpop =0;
    </script>
    <script type="text/javascript" src="/scripts/main/site/home.js"></script>
</c:if>

<%@include file="footer.jsp"%>

