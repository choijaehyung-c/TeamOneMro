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
                                    <div class="nav-link" onClick="getNewProductRequest('')">새상품 등록 요청건</div>
                                    <div class="nav-link" onClick="getModifyRequest('')">상품수정 요청건</div>                      
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
                            <div class="nav-link" onClick="callDeliveryList()">
                                배송관리
                            </div>
                            <div class="nav-link" >
                                Test4
                            </div>
                            
                        </div>
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content" style="height: 100%; width: 100%; table-layout: fixed;">

                <main style="height: 100%; width: 100%; margin-top:0px;">
           	
            
            <div id="mainVue" style="height: 100%; width: 100%;">
              
              
              <!-- !!!!!!!!상품등록신청 모달!!!!!!!!!!!! -->
              <div v-if="page[1].show" style="height: 100%; width: 100%; background: rgba(0,0,0,0.5); position: absolute; padding: 20px; z-index: 2;">
           	 	<div style="max-width: 100%; width: auto; display: table; background: #fff; border-radius: 10px; padding: 20px; z-index: 1;">
                   		<table class="dataTable-table">
                  
                   				<img :src="mroGetNewProductDetail.pr_image">
                   			<tbody>
                 
                            	<tr>
                                	<td>회사명</td><td>{{mroGetNewProductDetail.sp_name}}</td>
                                </tr>
                            	<tr>
                                	<td>전화번호</td><td>{{mroGetNewProductDetail.sp_tel}}</td>
                                </tr>
                            	<tr>
                                	<td>주소</td><td>{{mroGetNewProductDetail.sp_address}}</td>
                                </tr>
                                <tr>
                               		<td>상품명</td><td>{{mroGetNewProductDetail.pr_name}}</td>
                                </tr>
                                <tr>
                               		<td>가격</td><td>{{mroGetNewProductDetail.pr_price}}</td>
                                </tr>
                                <tr>
                               		<td>수량</td><td>{{mroGetNewProductDetail.pr_stock}}</td>
                                </tr>
                                <tr>
                               		<td>원산지</td><td>{{mroGetNewProductDetail.pr_origin}}</td>
                                </tr>
                                <tr>
                               		<td>정보</td><td>{{mroGetNewProductDetail.pr_info}}</td>
                                </tr>
                             
                            </tbody>	
                   		</table>
                   		<div style="text-align: center">
                   		<button @click="mroResponseNewProduct(mroGetNewProductDetail.pr_code, 'PC')"  type="button" class="btn btn-dark">등록</button>
                   		<button @click="mroResponseNewProduct(mroGetNewProductDetail.pr_code, 'AF')"  type="button" class="btn btn-dark">거절</button>
                   		<button @click="modalClose(1)" type="button" class="btn btn-dark">닫기</button>
                  		</div>
                  	</div>
                   	</div>
                		
                		
                		<template v-if="page[0].show" style="z-index: 3;">               		
           
        
                   		
                    <div class="container-fluid px-4">
                    
                    
                        <h1 style="padding:20px">상품 등록신청 리스트</h1>
                      
                     
                     <div class="dataTable-wrapper dataTable-loading no-footer sortable searchable fixed-columns">
                     <div class="dataTable-top">
                     	<div class="dataTable-search">
                     		<input class="dataTable-input" type="text" placeholder="상품명을 입력해주세요">
                     	</div>
                     </div>  
                        <div class="card mb-4">
                        	<div class="card-header">등록신청</div>
                            <div class="card-body">
                                <table id="datatablesSimple" class="dataTable-table">
                                    <thead>
                                        <tr>
                                            <th style="width: 10%;"><a>회사명</a></th>
                                            <th style="width: 10%;"><a>카테고리</a></th>
                                            <th style="width: 70%;"><a>상품명</a></th>
                                            <th style="width: 10%;" ><a></a></th>                                          
                                        </tr>
                                    </thead>
       
                                    <tbody>
                                        <tr v-for="rnp in getNewProductRequestList">
                                            <td @click="mroGetNewProductDetailPage(rnp.pr_code)">{{rnp.sp_name}}</td>
                                            <td @click="mroGetNewProductDetailPage(rnp.pr_code)">{{rnp.cate_name}}</td>
                                            <td @click="mroGetNewProductDetailPage(rnp.pr_code)">{{rnp.pr_name}}</td>
                                            <td style="text-align: center">
                                            	<button @click="mroResponseNewProduct(rnp.pr_code, 'PC')"  type="button" class="btn btn-dark">등록</button>
                   								<button @click="mroResponseNewProduct(rnp.pr_code, 'AF')"  type="button" class="btn btn-dark">거절</button>
                   							</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                   		</div>
                   	</template>
                   	
                   	
  
                   	
          	 <!-- !!!!!!!!상품수정신청 모달!!!!!!!!!!!! -->
              <div v-if="page[3].show" style="height: 100%; width: 100%; background: rgba(0,0,0,0.5); position: absolute; padding: 20px; z-index: 2;">
           	 	<div style="max-width: 100%; width: auto; display: table; background: #fff; border-radius: 10px; padding: 20px; z-index: 1;">
                   		<table class="dataTable-table">
                  
                   				<img :src="mroGetModifyRequestDetail.pr_image">
                   			<tbody>
                 
                            	<tr>
                                	<td>회사명</td><td>{{mroGetModifyRequestDetail.sp_name}}</td>
                                </tr>
                            	<tr>
                                	<td>전화번호</td><td>{{mroGetModifyRequestDetail.sp_tel}}</td>
                                </tr>
                            	<tr>
                                	<td>주소</td><td>{{mroGetModifyRequestDetail.sp_address}}</td>
                                </tr>
                                <tr>
                               		<td>상품명</td><td>{{mroGetModifyRequestDetail.pr_name}}</td>
                                </tr>
                                <tr>
                               		<td>가격</td><td>{{mroGetModifyRequestDetail.pr_price}}</td>
                                </tr>
                                <tr>
                               		<td>수량</td><td>{{mroGetModifyRequestDetail.pr_stock}}</td>
                                </tr>
                                <tr>
                               		<td>원산지</td><td>{{mroGetModifyRequestDetail.pr_origin}}</td>
                                </tr>
                                <tr>
                               		<td>정보</td><td>{{mroGetModifyRequestDetail.pr_info}}</td>
                                </tr>
                             
                            </tbody>	
                   		</table>
                   		<div style="text-align: center">
                   		<button @click="mroResponseModifyProduct(mroGetModifyRequestDetail.pr_code, mroGetModifyRequestDetail.pr_stcode, '1')"  type="button" class="btn btn-dark">승인</button>
                   		<button @click="mroResponseModifyProduct(mroGetModifyRequestDetail.pr_code, mroGetModifyRequestDetail.pr_stcode, '2')"  type="button" class="btn btn-dark">거절</button>
                   		<button @click="modalClose(3)" type="button" class="btn btn-dark">닫기</button>
                  		</div>
                  	</div>
                   	</div>

                   	
                   	<!-- 수정신청리스트 -->
                   	<template v-if="page[2].show" style="z-index: 3;">               		
                    <div class="container-fluid px-4">

                        <h1 style="padding:20px">상품 수정신청 리스트</h1>

                     <div class="dataTable-wrapper dataTable-loading no-footer sortable searchable fixed-columns">
                     <div class="dataTable-top">
                     	<div class="dataTable-search">
                     		<input class="dataTable-input" type="text" placeholder="상품명을 입력해주세요">
                     	</div>
                     </div>  
                        <div class="card mb-4">
                        	<div class="card-header">수정요청</div>
                            <div class="card-body">
                                <table id="datatablesSimple" class="dataTable-table">
                                    <thead>
                                        <tr>
                                            <th style="width: 10%;"><a>회사명</a></th>
                                            <th style="width: 10%;"><a>카테고리</a></th>
                                            <th style="width: 70%;"><a>상품명</a></th>
                                            <th style="width: 10%;" ><a></a></th>                                            
                                        </tr>
                                    </thead>
       
                                    <tbody>
                                        <tr v-for="rmp in modifyRequestList" v-if="rmp.pr_stcode =='MR'">
                                            <td @click="mroGetModifyProductDetailPage(rmp.pr_code, '1')">{{rmp.sp_name}}</td>
                                            <td @click="mroGetModifyProductDetailPage(rmp.pr_code, '1')">{{rmp.cate_name}}</td>
                                            <td @click="mroGetModifyProductDetailPage(rmp.pr_code, '1')">{{rmp.pr_name}}</td>
                                            <td style="text-align: center">
                                            	<button @click="mroResponseModifyProduct(rmp.pr_code, rmp.pr_stcode, '1')"  type="button" class="btn btn-dark">승인</button>
                   								<button @click="mroResponseModifyProduct(rmp.pr_code, rmp.pr_stcode, '2')"  type="button" class="btn btn-dark">거절</button>
                   							</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        
                        
                        
                        <div class="card mb-4">
                        	<div class="card-header">삭제요청</div>
                            <div class="card-body">
                                <table id="datatablesSimple" class="dataTable-table">
                                    <thead>
                                        <tr>
                                            <th style="width: 10%;"><a>회사명</a></th>
                                            <th style="width: 10%;"><a>카테고리</a></th>
                                            <th style="width: 70%;"><a>상품명</a></th>
                                            <th style="width: 10%;" ><a></a></th>                                         
                                        </tr>
                                    </thead>
       
                                    <tbody>
                                        <tr v-for="rmp in modifyRequestList" v-if="rmp.pr_stcode =='DR'">
                                            <td @click="mroGetModifyProductDetailPage(rmp.pr_code, '2')">{{rmp.sp_name}}</td>
                                            <td @click="mroGetModifyProductDetailPage(rmp.pr_code, '2')">{{rmp.cate_name}}</td>
                                            <td @click="mroGetModifyProductDetailPage(rmp.pr_code, '2')">{{rmp.pr_name}}</td>
                                            <td style="text-align: center">
                                            	<button @click="mroResponseModifyProduct(rmp.pr_code, rmp.pr_stcode, '1')"  type="button" class="btn btn-dark">승인</button>
                   								<button @click="mroResponseModifyProduct(rmp.pr_code, rmp.pr_stcode, '2')"  type="button" class="btn btn-dark">거절</button>
                   							</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        
                        
                        
                        
                      
                    </div>
                   		</div>
                   	</template>
                 


                   	

                   	</div>
                   	
                   	
                   	
                   	
                   	
                   	 <div id="deliveryVue" style="height: 100%; width: 100%;">
                   	 	<template v-if="deliveryPage[0].show">
                   	 	<div v-for="dl in deliveryList" >
                   	 	 <h6>&nbsp </h6>
                   	 		<div>
                   	 			<div>배송원:{{dl.dv_name}}</div><div>주문자:{{dl.sp_name}}</div><div>배송지:{{dl.sp_address}}</div><div>전화번호:{{dl.sp_tel}}</div><div>{{dl.ds_name}}</div>
                   	 				
                   	 				<button v-if="dl.dl_dscode==1"  @click="insertsdcode(dl.dl_code, 2)"  type="button" class="btn btn-dark">배송시작</button>
                   	 				
                   	 				<button v-if="dl.dl_dscode==2" @click="insertsdcode(dl.dl_code, 3)"  type="button" class="btn btn-dark">배송완료</button>
                   	 			
                   	 		</div>
                   		</div>
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
        <script src="${pageContext.request.contextPath}/resources/js/HSMmro.js"></script>
    </body>
</html>
