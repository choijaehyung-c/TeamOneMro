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
        <link href="resources/css/innew.css" rel="stylesheet" />
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
                                    <div class="nav-link" onClick="mroOrderList()">주문목록</div>
                                    <div class="nav-link" onClick="mroRefundList()">반품목록</div>
                                    <div class="nav-link" onClick="mroExchangeList()">교환목록</div>
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
                            
                            <div class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages3" aria-expanded="false" aria-controls="collapsePages3">                                
                                발주관리
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </div>
                            <div class="collapse" id="collapsePages3" aria-labelledby="headingfour" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">                                
                                    <div class="nav-link" onClick="requestOrder()">발주하기</div>                
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
                <main style="height: 100%; width: 100%;">
                	<div id="mainVue" style="height: 100%; width: 100%;">
                		<template v-if="page[0].show">
                		<div class="container-fluid px-4">
							<h1 class="mt-4">공급사 목록</h1>
							<ol class="breadcrumb mb-4">
								<li class="breadcrumb-item"><a href="/">메인페이지</a></li>
								<li class="breadcrumb-item active">공급사 목록</li>
							</ol>
								<input type="text" class="form-control" name="sp_code" placeholder="공급사 이름을 검색하세요." /><span  class="btn btn-secondary btn_two me-2 my-1" type="button" @click="search()">검 색</span>
								<div class="btn btn-secondary me-2 my-1" type="button" id="insSp" data-bs-toggle="modal" data-bs-target="#ModalLg">새 공급사 등록</div>
								                		<div class="modal fade" id="ModalLg" tabindex="-1" aria-labelledby="myLargeModalLabel" style="display: none;" aria-hidden="true">
                                                        <div class="modal-dialog modal-lg" role="document">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title" >새 공급사 등록</h5>
                                                                    <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close" onClick="close()"></button>
                                                                </div>
                                                                <div class="modal-body">
                                                                 	<table><tr class='title'><th scope='row'><span class='title_d'>업체명</span></th><td colspan='2'><input type="text" class="text" id='subject' name="sp_name" value=""></tr>
                                                                 			<tr class='title'><th scope='row'><span class='title_d'>업체코드</span></th><td colspan='2'><input type="text" class="text" id='subject' name="sp_code" value=""></tr>
                                                                 			<tr class='title'><th scope='row'><span class='title_d'>임시비밀번호</span></th><td colspan='2'><input type="password" class="text" id='subject' name="sp_pwd" value=""></tr>
                                                                 			<tr class='title'><th scope='row'><span class='title_d'>지부장</span></th><td colspan='2'><input type="text" class="text" id='subject' name="sp_ceo" value=""></tr>
                                                                 			<tr class='title'><th scope='row'><span class='title_d'>주소</span></th><td colspan='2'><input type="text" class="text" id='subject' name="sp_address" value=""></tr>
                                                                 			<tr class='title'><th scope='row'><span class='title_d'>연락처</span></th><td colspan='2'><input type="text" class="text" id='subject' name="sp_tel" value=""></tr>
                                                                 			<tr class='title'><th scope='row'><span class='title_d'>사업자번호</span></th><td colspan='2'><input type="text" class="text" id='subject' name="sp_corpnum" value=""></tr>
                                           
                                                                 			<tr class='title'><th scope='row'><span class='title_d'>업태</span></th><td colspan='2'><select name="sp_btype"><option value='C'>제조업</option><option value='D'>유통업</option><option value='G'>도매업</option></select>
                                                                 			<tr class='title'><th scope='row'><span class='title_d'>업종</span></th><td colspan='2'><select name="sp_bkind"><option value='KS'>사무용품</option><option value='KB'>식음료</option><option value='KL'>생활용품</option><option value='KC'>청소용품</option></select>                                                                
                                                                 	</table>                        
                                                                </div>
                                                                <div class="modal-footer">
                                                                <button class="btn btn-primary" type="button" data-bs-dismiss="modal" onClick="close()">Close</button>
                                                                <button class="btn btn-primary" type="button" @click="add()">Add</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="card mb-4">
								<div class="card-header">
									<i class="fas fa-table me-1"></i> 공급사 목록
								</div>  
								<div class="card-body">            		               		
                		<table id="datatablesSimple"class="dataTable-table"><tr><thead><th>이름</th><th>위치</th><th >업종</th><th>요청</th></tr></thead>
                		<tbody>
                	
                		<tr v-for="ii in supplyList" ><td data-bs-toggle="modal" data-bs-target="#exampleModalLg" @click="datata(ii.sp_code)">{{ii.sp_name}}</td><td>{{ii.sp_address}}</td><td>{{ii.bk_name}}</td><td><button  class="btn btn-secondary me-2 my-1" type="button" @click="search()">삭 제</button></td></tr>
                		</tbody>
                		
                		   <div class="modal fade" id="exampleModalLg" tabindex="-1" aria-labelledby="myLargeModalLabel" style="display: none;" aria-hidden="true">
                                            <div class="modal-dialog modal-lg" role="document">
                                                       <div class="modal-content">
                                                              <div class="modal-header">
                                                                   <div class="modal-title" >{{sp_name}}</div>
                                                                   <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                                                              </div>
                                                              <div class="modal-body"><p>상세정보.</p></div>
                                                              <div class="modal-footer"><button class="btn btn-primary" type="button" data-bs-dismiss="modal">Close</button></div>
                                                      </div>
                                            </div>                                          
                             </div>    
                             </div>          
                		</table>    
   	
                		</template>               
                		
                		<template v-if="page[2].show">
                		<div class="title">고객사 목록</div>
                		 
                		<input type="text" class="form-control" name="cl_code" placeholder="고객사 이름을 검색하세요."/><button  class="btn btn-secondary btn_two me-2 my-1" type="button"  @click="search()">검 색</button>
                		<div class="btn btn-secondary me-2 my-1" type="button" id="insSp" data-bs-toggle="modal" data-bs-target="#ModalLg">새 고객사 등록</div>
                		<div class="modal fade" id="ModalLg" tabindex="-1" aria-labelledby="myLargeModalLabel" style="display: none;" aria-hidden="true">
                                                        <div class="modal-dialog modal-lg" role="document">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title" v-for="ii in clientList">{{ii.cl_name}}</h5>
                                                                    <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                </div>
                                                                <div class="modal-body"><p>상세정보</p></div>
                                                                <div class="modal-footer"><button class="btn btn-primary" type="button" data-bs-dismiss="modal">Close</button></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    
                		<table id="customers"><tr><th>고객사 이름</th><th>위치</th><th>요청</th></tr>                		                		
                		<tr v-for="ii in clientList" ><td  data-bs-toggle="modal" data-bs-target="#exampleModalLg" @click="datata(ii.cl_code)">{{ii.cl_name}}</td><td>{{ii.cl_address}}</td><td><button  class="btn btn-secondary me-2 my-1" type="button" @click="search()">삭 제</button></td></tr>                		
                		<div class="modal fade" id="exampleModalLg" tabindex="-1" aria-labelledby="myLargeModalLabel" style="display: none;" aria-hidden="true">
                                                        <div class="modal-dialog modal-lg" role="document">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title" v-for="ii in clientList">{{ii.cl_name}}</h5>
                                                                    <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                </div>
                                                                <div class="modal-body"><p>상세정보</p></div>
                                                                <div class="modal-footer"><button class="btn btn-primary" type="button" data-bs-dismiss="modal">Close</button></div>
                                                            </div>
                                                        </div>
                                                    </div>
                		</table>
                		</template>
                	</div>
                </main>
            </div>
        </div>
        <script src="resources/js/scripts.js"></script>  
        <script src="resources/js/datatables-simple-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
		<script src="${pageContext.request.contextPath}/resources/vue/vue.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/js.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/mro.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/vueiyj.js"></script>

        
    </body>
</html>
