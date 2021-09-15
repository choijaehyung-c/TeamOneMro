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
        <link href="${pageContext.request.contextPath}/resources/css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
    </head>
    
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <a class="navbar-brand ps-3" href="/">MRONE_SUPPLY</a>
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
                            <div class="nav-link" onclick="change1()">
                                
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
                            
                            <div class="nav-link" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                                
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
                   
                            
                            
  							<div id="mainVueTwo">	
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages5" aria-expanded="false" aria-controls="collapsePages5">
                                상품관리
                                <div class="sb-sidenav-collapse-arrow"><svg class="svg-inline--fa fa-angle-down fa-w-10" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="angle-down" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512" data-fa-i2svg=""><path fill="currentColor" d="M143 352.3L7 216.3c-9.4-9.4-9.4-24.6 0-33.9l22.6-22.6c9.4-9.4 24.6-9.4 33.9 0l96.4 96.4 96.4-96.4c9.4-9.4 24.6-9.4 33.9 0l22.6 22.6c9.4 9.4 9.4 24.6 0 33.9l-136 136c-9.2 9.4-24.4 9.4-33.8 0z"></path></svg><!-- <i class="fas fa-angle-down"></i> Font Awesome fontawesome.com --></div>
                            </a>
                            <div class="collapse" id="collapsePages5" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion" style="">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                    <a  @click="supplyGetCategoryPage(), supplyAllProductListPage()" class="nav-link" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseAuth" aria-expanded="false" aria-controls="pagesCollapseAuth">
                                        판매중인 상품
                                        <div class="sb-sidenav-collapse-arrow"><svg class="svg-inline--fa fa-angle-down fa-w-10" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="angle-down" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512" data-fa-i2svg=""><path fill="currentColor" d="M143 352.3L7 216.3c-9.4-9.4-9.4-24.6 0-33.9l22.6-22.6c9.4-9.4 24.6-9.4 33.9 0l96.4 96.4 96.4-96.4c9.4-9.4 24.6-9.4 33.9 0l22.6 22.6c9.4 9.4 9.4 24.6 0 33.9l-136 136c-9.2 9.4-24.4 9.4-33.8 0z"></path></svg><!-- <i class="fas fa-angle-down"></i> Font Awesome fontawesome.com --></div>
                                    </a>
                                    <div class="collapse" id="pagesCollapseAuth" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages" style="">
                                        <nav class="sb-sidenav-menu-nested nav">
                                            <div  v-if="display[0].show">
                                    			<div v-for="cl in categoryList">
                                    				<a class="nav-link" @click="callCategoryPoductList(cl.cate)">{{cl.cate_name}}</a>
                                    			</div>
                                           </div>
                                        </nav>
                                    </div>
                                    <a @click="supplyPRAFProductListPage()" class="nav-link" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseError" aria-expanded="false" aria-controls="pagesCollapseError">
                                        새상품 등록 요청건
                                    </a>
                                    <a @click="supplyMRDRDAProductListPage()" class="nav-link" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseError" aria-expanded="false" aria-controls="pagesCollapseError">
                                        상품수정 요청건
                                    </a>
                                </nav>
                            </div>

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
                            <div class="nav-link" onclick="change2()">
                                Test3
                            </div>
                            <div class="nav-link" onclick="change3()">
                                Test4
                            </div>
                            
                            <div class="nav-link" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="true" aria-controls="collapsePages">
                                <div class="sb-nav-link-icon"><svg class="svg-inline--fa fa-book-open fa-w-18" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="book-open" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" data-fa-i2svg=""><path fill="currentColor" d="M542.22 32.05c-54.8 3.11-163.72 14.43-230.96 55.59-4.64 2.84-7.27 7.89-7.27 13.17v363.87c0 11.55 12.63 18.85 23.28 13.49 69.18-34.82 169.23-44.32 218.7-46.92 16.89-.89 30.02-14.43 30.02-30.66V62.75c.01-17.71-15.35-31.74-33.77-30.7zM264.73 87.64C197.5 46.48 88.58 35.17 33.78 32.05 15.36 31.01 0 45.04 0 62.75V400.6c0 16.24 13.13 29.78 30.02 30.66 49.49 2.6 149.59 12.11 218.77 46.95 10.62 5.35 23.21-1.94 23.21-13.46V100.63c0-5.29-2.62-10.14-7.27-12.99z"></path></svg><!-- <i class="fas fa-book-open"></i> Font Awesome fontawesome.com --></div>
                                상품관리
                                <div class="sb-sidenav-collapse-arrow"><svg class="svg-inline--fa fa-angle-down fa-w-10" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="angle-down" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512" data-fa-i2svg=""><path fill="currentColor" d="M143 352.3L7 216.3c-9.4-9.4-9.4-24.6 0-33.9l22.6-22.6c9.4-9.4 24.6-9.4 33.9 0l96.4 96.4 96.4-96.4c9.4-9.4 24.6-9.4 33.9 0l22.6 22.6c9.4 9.4 9.4 24.6 0 33.9l-136 136c-9.2 9.4-24.4 9.4-33.8 0z"></path></svg><!-- <i class="fas fa-angle-down"></i> Font Awesome fontawesome.com --></div>
                            </div>
                            	
                            	
                            	
                            
                        </div>
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content" style="height: 100%; width: 100%; table-layout: fixed;">
                <main style="height: 100%; width: 100%; margin-top:0px;">
            		<div id="mainVue" style="height: 100%; width: 100%;">
           			
           			<template v-if="display[0].show">	
           				 <!-- !!!!!!!!상품등록신청중인 상품정보 모달!!!!!!!!!!!! -->
              <div v-if="modal.show" style="height: 100%; width: 100%; background: rgba(0,0,0,0.5); position: absolute; padding: 20px; z-index: 2;">
           	 	<div style="max-width: 100%; width: auto; display: table; background: #fff; border-radius: 10px; padding: 20px; z-index: 1;">
                   		<table class="dataTable-table">
                  
                   				<img :src="detail.pr_image">
                   			<tbody>
                                <tr>
                               		<td>상품명</td><td><input style="width: 300px;" type="text" v-model="detail.pr_name"></td>
                                </tr>
                                <tr>
                               		<td>가격</td><td><input style="width: 300px;" type="text" v-model="detail.pr_price"></td>
                                </tr>
                                <tr>
                               		<td>수량</td><td><input readonly style="width: 300px;" type="text" v-model="detail.pr_stock"></td>
                                </tr>
                                <tr>
                               		<td>원산지</td><td><input style="width: 300px;" type="text" v-model="detail.pr_origin"></td>
                                </tr>
                                <tr>
                               		<td>정보</td><td><input style="width: 300px;" type="text" v-model="detail.pr_info"></td>
                                </tr>
                             
                            </tbody>	
                   		</table>
                   		<div  style="text-align: center">
                   		<button @click="supplyRequestModify(detail.pr_spcode, detail.pr_code, detail.pr_tax,
                   											detail.pr_spbkind, 'MR', detail.pr_image,
                   											detail.pr_name, detail.pr_price, detail.pr_stock,
                   											detail.pr_origin, detail.pr_info, detail.cate,
                   											detail.cate_name)"  type="button" class="btn btn-dark">수정요청</button>
                   		<button @click="supplyRequestDelete(detail.pr_spcode, detail.pr_code, detail.pr_tax,
                   											detail.pr_spbkind, 'DR', detail.pr_image,
                   											detail.pr_name, detail.pr_price, detail.pr_stock,
                   											detail.pr_origin, detail.pr_info, detail.cate,
                   											detail.cate_name)"  type="button" class="btn btn-dark">삭제요청</button>
                   		<button @click="modalClose()" type="button" class="btn btn-dark">닫기</button>
                  		</div>
                   		
                  	</div>
                   	</div>
           				
           				
           				
           				
           					<div class="container-fluid px-4">
                        <h6>&nbsp </h6>
                     <div class="dataTable-wrapper dataTable-loading no-footer sortable searchable fixed-columns">
                     <div  class="dataTable-top">
                     	<div class="dataTable-search">
                     		<input  @change="search1()" v-on:input="search1" v-bind:value="searchWord"  class="dataTable-input" type="text" placeholder="상품명을 입력해주세요" >
                     	
                     	</div>
                     </div>  
                        <div class="card mb-4">
                        	<div class="card-header">판매중인 상품</div>
                            <div class="card-body">
                                <table id="datatablesSimple" class="dataTable-table">
                                    <thead>
                                        <tr>
                                            <th style="width: 10%;"><a>카테고리</a></th>
                                            <th style="width: 60%;"><a>상품명</a></th>
                                            <th style="width: 20%;"><a>수량</a></th>
                                            <th style="width: 10%;" ><a></a></th>                                          
                                        </tr>
                                    </thead>
       
                                    <tbody v-if="display[1].show">
                                        <tr  v-for="sap in list">
                                            <td @click="productDetail(sap.pr_code, 'PC')">{{sap.cate_name}}</td>
                                            <td @click="productDetail(sap.pr_code, 'PC')">{{sap.pr_name}}</td>
                                            <td>
                                            		<input type="text" v-model="sap.pr_stock">
                                            		<button @click="supplyModifyStock(sap.pr_code, sap.pr_stock)"  type="button" class="btn btn-dark">수량변경</button>
                                            </td>
                                            <td style="text-align: center">
                                       
                   								<button @click="supplyRequestDelete(sap.pr_spcode, sap.pr_code, sap.pr_tax,
                   											sap.pr_spbkind, 'DR', sap.pr_image,
                   											sap.pr_name, sap.pr_price, sap.pr_stock,
                   											sap.pr_origin, sap.pr_info, sap.cate,
                   											sap.cate_name)"  type="button" class="btn btn-dark">삭제요청</button>
                   							</td>
                                        </tr>
                                        </tbody>
                                    <!-- 카테고리별 리스트 -->
                                    <tbody  v-if="display[2].show">
                                    <div>
                                        <tr v-for="sap in list " v-if="sap.cate == categoryCode">
                                            <td @click="productDetail(sap.pr_code, 'PC')">{{sap.cate_name}}</td>
                                            <td @click="productDetail(sap.pr_code, 'PC')">{{sap.pr_name}}</td>
                                            <td>
                                            		<input type="text" v-model="sap.pr_stock">
                                            		<button @click="supplyModifyStock(sap.pr_code, sap.pr_stock)"  type="button" class="btn btn-dark">수량변경</button>
                                            </td>
                                            <td style="text-align: center">
                                            	<button @click="supplyRequestDelete(sap.pr_spcode, sap.pr_code, sap.pr_tax,
                   											sap.pr_spbkind, 'DR', sap.pr_image,
                   											sap.pr_name, sap.pr_price, sap.pr_stock,
                   											sap.pr_origin, sap.pr_info, sap.cate,
                   											sap.cate_name)"  type="button" class="btn btn-dark">삭제요청</button>
                   							</td>
                                        </tr>
                                       </div>
                                   </tbody>
                                   
                                   <tbody v-if="display[5].show">
                                    <div>
                                        <tr v-for="sap in list " v-if="sap.pr_name.includes(searchWord)">
                                            <td @click="productDetail(sap.pr_code, 'PC')">{{sap.cate_name}}</td>
                                            <td @click="productDetail(sap.pr_code, 'PC')">{{sap.pr_name}}</td>
                                            <td>
                                            		<input type="text" v-model="sap.pr_stock">
                                            		<button @click="supplyModifyStock(sap.pr_code, sap.pr_stock)"  type="button" class="btn btn-dark">수량변경</button>
                                            </td>
                                            <td style="text-align: center">
                                            	<button @click="supplyRequestDelete(sap.pr_spcode, sap.pr_code, sap.pr_tax,
                   											sap.pr_spbkind, 'DR', sap.pr_image,
                   											sap.pr_name, sap.pr_price, sap.pr_stock,
                   											sap.pr_origin, sap.pr_info, sap.cate,
                   											sap.cate_name)"  type="button" class="btn btn-dark">삭제요청</button>
                   							</td>
                                        </tr>
                                       </div>
 
                                    </tbody>
                                </table>
                            </div>
                                       
                        </div>
                    </div>
                   		</div>
           				</template>
           				
           				
           				
           				
           		<template v-if="display[3].show">		
           						 <!-- !!!!!!!!상품등록신청중인 상품 모달!!!!!!!!!!!! -->
              <div v-if="modal.show" style="height: 100%; width: 100%; background: rgba(0,0,0,0.5); position: absolute; padding: 20px; z-index: 2;">
           	 	<div style="max-width: 100%; width: auto; display: table; background: #fff; border-radius: 10px; padding: 20px; z-index: 1;">
                   		<table class="dataTable-table">
                  
                   				<img :src="detail.pr_image">
                   			<tbody>
                                <tr>
                               		<td>상품명</td><td><input style="width: 300px;" type="text" v-model="detail.pr_name"></td>
                                </tr>
                                <tr>
                               		<td>가격</td><td><input style="width: 300px;" type="text" v-model="detail.pr_price"></td>
                                </tr>
                                <tr>
                               		<td>수량</td><td><input readonly style="width: 300px;" type="text" v-model="detail.pr_stock"></td>
                                </tr>
                                <tr>
                               		<td>원산지</td><td><input style="width: 300px;" type="text" v-model="detail.pr_origin"></td>
                                </tr>
                                <tr>
                               		<td>정보</td><td><input style="width: 300px;" type="text" v-model="detail.pr_info"></td>
                                </tr>
                             
                            </tbody>	
                   		</table>
                   		<div  style="text-align: center">
                   		<div style="text-align: center">
                   			<button @click="supplyRequestCancel(detail.pr_code, detail.pr_stcode)" type="button" class="btn btn-dark">요청삭제</button>
                   			<button @click="modalClose()" type="button" class="btn btn-dark">닫기</button>
                  		</div>
                  		</div>
                   		
                  	</div>
                   	</div>
           				
           				
           				
           							<!-- 상품신청 입력받는 모달 -->
           				<div v-if="modal2.show" style="height: 100%; width: 100%; background: rgba(0,0,0,0.5); position: absolute; padding: 20px; z-index: 2;">
           	 	<div style="max-width: 100%; width: auto; display: table; background: #fff; border-radius: 10px; padding: 20px; z-index: 1;">
                   		<table class="dataTable-table">
                  
                
                   			<tbody>
                                <tr>
                               		<td>사진</td><td><input  style="width: 300px;" type="text" v-model="pr_image"></td>
                                </tr>
                                <tr>
                               		<td>카테고리</td><td><select v-model="cate" style="width: 300px;" id="CG">
                               								<option v-for="selectCate in categoryList2"  :value="selectCate.cate" >{{selectCate.cate_name}}</option>
                               						   </select>
                               					  </td>	
                               							
                                </tr>
                                <tr>
                               		<td>상품명</td><td><input style="width: 300px;" type="text" v-model="pr_name"></td>
                                </tr>
                                <tr>
                               		<td>가격</td><td><input style="width: 300px;" type="text" v-model="pr_price"></td>
                                </tr>
                                <tr>
                               		<td>수량</td><td><input style="width: 300px;" type="text" v-model="pr_stock"></td>
                                </tr>
                                <tr>
                               		<td>원산지</td><td><input style="width: 300px;" type="text" v-model="pr_origin"></td>
                                </tr>
                                <tr>
                               		<td>정보</td><td><input style="width: 300px;" type="text" v-model="pr_info"></td>
                                </tr>
                             
                            </tbody>	
                   		</table>
                   		<div  style="text-align: center">
                   		<div style="text-align: center">
                   			<button @click="supplyRequestNewProduct()" type="button" class="btn btn-dark">등록요청</button>
                   			<button @click="modal2.show = false" type="button" class="btn btn-dark">닫기</button>
                  		</div>
                  		</div>
                   		
                  	</div>
                   	</div>
           				
           				
           				
           				
           				<!-- 상품 등록신청 리스트 -->
           				
           					<div class="container-fluid px-4">
                        <h6>&nbsp </h6>
                     <div class="dataTable-wrapper dataTable-loading no-footer sortable searchable fixed-columns">
                     <div  class="dataTable-top">
                     	<button @click="supplyRequestNewProductModal()" type="button" class="btn btn-dark">물픔등록신청</button>
                     	<div class="dataTable-search">
                     		<input @change="search2()" v-on:input="search2" v-bind:value="searchWord" class="dataTable-input" type="text" placeholder="상품명을 입력해주세요" >
                     	
                     	</div>
                     </div>  
                        <div class="card mb-4">
                        	<div class="card-header">등록요청중인 상품</div>
                            <div class="card-body">
                                <table id="datatablesSimple" class="dataTable-table">
                                    <thead v-if="display[6].show">
                                        <tr>
                                            <th style="width: 10%;"><a>카테고리</a></th>
                                            <th style="width: 60%;"><a>상품명</a></th>
                                            <th style="width: 20%;"><a>상태</a></th>
                                            <th style="width: 10%;" ><a></a></th>                                          
                                        </tr>
                                    </thead>
       
                                    <tbody v-if="display[7].show">
                                        <tr v-for="PRAF in list">
                                            <td @click="productDetail(PRAF.pr_code, PRAF.pr_stcode)">{{PRAF.cate_name}}</td>
                                            <td @click="productDetail(PRAF.pr_code, PRAF.pr_stcode)">{{PRAF.pr_name}}</td>
                                            <td @click="productDetail(PRAF.pr_code, PRAF.pr_stcode)">{{PRAF.st_name}}</td>
                                            
                                            <td style="text-align: center">
                   								<button @click="supplyRequestCancel(PRAF.pr_code, PRAF.pr_stcode)" type="button" class="btn btn-dark">요청삭제</button>
                   							</td>
                                        </tr>
                                    </tbody>
                                    
                                    <tbody>
                                        <tr v-for="PRAF in list" v-if="PRAF.pr_name.includes(searchWord)" >
                                            <td @click="productDetail(PRAF.pr_code, PRAF.pr_stcode)">{{PRAF.cate_name}}</td>
                                            <td @click="productDetail(PRAF.pr_code, PRAF.pr_stcode)">{{PRAF.pr_name}}</td>
                                            <td @click="productDetail(PRAF.pr_code, PRAF.pr_stcode)">{{PRAF.st_name}}</td>
                                            
                                            <td style="text-align: center">
                   								<button @click="supplyRequestCancel(PRAF.pr_code, PRAF.pr_stcode)" type="button" class="btn btn-dark">요청삭제</button>
                   							</td>
                                        </tr>
                                    </tbody>
                               
                                </table>
                            </div>
                                
                        </div>
                    </div>
                   		</div>
           				</template>
           				
           				
           				
           				
           				<template v-if="display[4].show" style="z-index: 3;">  
           				
           				<!--  수정,삭제요청상품 모달 -->
           				<div v-if="modal.show" style="height: 100%; width: 100%; background: rgba(0,0,0,0.5); position: absolute; padding: 20px; z-index: 2;">
           	 	<div style="max-width: 100%; width: auto; display: table; background: #fff; border-radius: 10px; padding: 20px; z-index: 1;">
                   		<table class="dataTable-table">
                  
                   				<img :src="detail.pr_image">
                   			<tbody>
                                <tr>
                               		<td>상품명</td><td><input style="width: 300px;" type="text" v-model="detail.pr_name"></td>
                                </tr>
                                <tr>
                               		<td>가격</td><td><input style="width: 300px;" type="text" v-model="detail.pr_price"></td>
                                </tr>
                                <tr>
                               		<td>수량</td><td><input readonly style="width: 300px;" type="text" v-model="detail.pr_stock"></td>
                                </tr>
                                <tr>
                               		<td>원산지</td><td><input style="width: 300px;" type="text" v-model="detail.pr_origin"></td>
                                </tr>
                                <tr>
                               		<td>정보</td><td><input style="width: 300px;" type="text" v-model="detail.pr_info"></td>
                                </tr>
                             
                            </tbody>	
                   		</table>
                   		<div  style="text-align: center">
                   		<div style="text-align: center">
                   			<button @click="supplyRequestCancel(detail.pr_code, detail.pr_stcode)" type="button" class="btn btn-dark">요청삭제</button>
                   			<button @click="modalClose()" type="button" class="btn btn-dark">닫기</button>
                  		</div>
                  		</div>
                   		
                  	</div>
                   	</div>
           				
           				
           				
           				             		
                    <div class="container-fluid px-4">

                        <h6>&nbsp </h6>

                     <div class="dataTable-wrapper dataTable-loading no-footer sortable searchable fixed-columns">
                     <div class="dataTable-top">
                     	<div class="dataTable-search">
                     		<input @change="search3()" v-on:input="search3" v-bind:value="searchWord" class="dataTable-input" type="text" placeholder="상품명을 입력해주세요">
                     	</div>
                     </div>  
                        

                        
                        <div class="card mb-4">
                        	<div class="card-header">수정, 삭제요청중인 상품</div>
                            <div class="card-body">
                                <table id="datatablesSimple" class="dataTable-table">
                                    <thead>
                                        <tr>
                                            <th style="width: 10%;"><a>카테고리</a></th>
                                            <th style="width: 60%;"><a>상품명</a></th>
                                            <th style="width: 20%;"><a>상태</a></th>
                                            <th style="width: 10%;" ><a></a></th>                                         
                                        </tr>
                                    </thead>
       
                                    <tbody v-if="display[8].show">
                                        <tr v-for="MRDRDA in list">
                                            <td @click="productDetail(MRDRDA.pr_code, MRDRDA.pr_stcode)">{{MRDRDA.cate_name}}</td>
                                            <td @click="productDetail(MRDRDA.pr_code, MRDRDA.pr_stcode)">{{MRDRDA.pr_name}}</td>
                                            <td @click="productDetail(MRDRDA.pr_code, MRDRDA.pr_stcode)">{{MRDRDA.st_name}}</td>
                                            <td style="text-align: center">
                                            	<button @click="supplyRequestCancel(MRDRDA.pr_code, MRDRDA.pr_stcode)" type="button" class="btn btn-dark">요청삭제</button>
                   							</td>
                                        </tr>
                                    </tbody>
                                    
                                    <tbody v-if="display[9].show">
                                        <tr v-for="MRDRDA in list" v-if="MRDRDA.pr_name.includes(searchWord)">
                                            <td @click="productDetail(MRDRDA.pr_code, MRDRDA.pr_stcode)">{{MRDRDA.cate_name}}</td>
                                            <td @click="productDetail(MRDRDA.pr_code, MRDRDA.pr_stcode)">{{MRDRDA.pr_name}}</td>
                                            <td @click="productDetail(MRDRDA.pr_code, MRDRDA.pr_stcode)">{{MRDRDA.st_name}}</td>
                                            <td style="text-align: center">
                                            	<button @click="supplyRequestCancel(MRDRDA.pr_code, MRDRDA.pr_stcode)" type="button" class="btn btn-dark">요청삭제</button>
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
           		</main>
           	</div> 
        </div>
        <!-- <component v-bind:is="currentView" v-bind:aaqqd="mssg"></component> -->
        <script src="${pageContext.request.contextPath}/resources/js/scripts.js"></script>  
        <script src="${pageContext.request.contextPath}/resources/js/datatables-simple-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
		<script src="${pageContext.request.contextPath}/resources/vue/vue.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/js.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/innew.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/hsm.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/vuecjh.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/HSMsup.js"></script>
    </body>
</html>



