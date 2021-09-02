<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>MRONE</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="resources/css/styles.css" rel="stylesheet" />
        <script src="${pageContext.request.contextPath}/resources/js/js.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/innew.js"></script>
         <script src="${pageContext.request.contextPath}/resources/vue/hsm.js"></script>
        <script src="${pageContext.request.contextPath}/resources/vue/vue.js"></script>
       
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
    </head>
    
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <a class="navbar-brand ps-3" href="/">MRONE</a>
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <div class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></div>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><div class="dropdown-item" >Settings</div></li>
                        <li><div class="dropdown-item" >Activity Log</div></li>
                        <li><hr class="dropdown-divider" /></li>
                        <li><div class="dropdown-item" onclick="readyAccessMro('-1','/AccessOutMro')">Logout</div></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">Core</div>
                            <div class="nav-link">
                                
                                Dashboard
                            </div>
                            <div class="sb-sidenav-menu-heading">Interface</div>
                            <div class="nav-link collapsed" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                
                                 거래처관리
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </div>
                            <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <div class="nav-link" onClick="clientList()">고객사 목록</div>
                                    <div class="nav-link" onClick="supplyList()">공급사 목록</div>
                                </nav>
                            </div>
                            
                            <div class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                                
                                주문관리
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </div>
                            <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                    <div class="nav-link" onClick="mroOrderList()" >주문목록</div>
                                    <div class="nav-link" >반품목록</div>
                                    <div class="nav-link" >교환목록</div>                                                                    
                                </nav>  
                            </div>
                            
                            
                            <div class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages2" aria-expanded="false" aria-controls="collapsePages2">                                
                                상품관리
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </div>
                            <div class="collapse" id="collapsePages2" aria-labelledby="headingThree" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">                                
                                    <div class="nav-link" onClick="getNewProductRequest()">새상품 등록 요청건</div>
                                    <div class="nav-link" onClick="getModifyRequest()">상품수정 요청건</div>                      
                                </nav>  
                            </div>
                            
                            
                            <div class="sb-sidenav-menu-heading">Addons</div>
                            <div class="nav-link" >
                                Test3
                            </div>
                            <div class="nav-link" >
                                Test4
                            </div>
                            
                        </div>
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                	<!-- <iframe src="" style="height: 90vh; width:100%; frameborder=0; overflow:auto; scrolling=no; " id="main_iframe">Please visit in a browser that supports iFrame.</iframe> -->
                	<div id="mOrderList"></div>
                	<div>${WorderList}</div>
                </main>
            </div>
        </div>
        <script src="resources/js/scripts.js"></script>  
        <script src="resources/js/datatables-simple-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
		<script src="${pageContext.request.contextPath}/resources/vue/test.js"></script>
    </body>
</html>
