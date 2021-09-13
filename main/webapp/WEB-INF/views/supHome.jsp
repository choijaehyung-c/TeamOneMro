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
        <link href="${pageContext.request.contextPath}/resources/css/supplyIYJ.css" rel="stylesheet" />
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
                            
                           <div class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                                
                                수주관리
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </div>
                            <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                    <div class="nav-link" onClick="orderWaitList()">발주대기목록</div>
                                    <div class="nav-link" onClick="orderReceiveList()">출고처리</div>
                                </nav>  
                            </div>
                   
                            
                            
                            <div class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages55" aria-expanded="false" aria-controls="collapsePages55">                                
                                교환 / 반품 관리
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </div>
                            <div class="collapse" id="collapsePages55" aria-labelledby="headingThree" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">                                
                                    <div class="nav-link" onClick="getRefundListForm()">반품 요청 리스트</div>
                                    <div class="nav-link" onClick="getExchangeListForm()">교환 요청 리스트</div>                      
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
                            <!--  -->
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages5" aria-expanded="false" aria-controls="collapsePages5">
                                Pages
                                <div class="sb-sidenav-collapse-arrow"><svg class="svg-inline--fa fa-angle-down fa-w-10" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="angle-down" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512" data-fa-i2svg=""><path fill="currentColor" d="M143 352.3L7 216.3c-9.4-9.4-9.4-24.6 0-33.9l22.6-22.6c9.4-9.4 24.6-9.4 33.9 0l96.4 96.4 96.4-96.4c9.4-9.4 24.6-9.4 33.9 0l22.6 22.6c9.4 9.4 9.4 24.6 0 33.9l-136 136c-9.2 9.4-24.4 9.4-33.8 0z"></path></svg><!-- <i class="fas fa-angle-down"></i> Font Awesome fontawesome.com --></div>
                            </a>
                            <div class="collapse" id="collapsePages5" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion" style="">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                    <a class="nav-link" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseAuth" aria-expanded="false" aria-controls="pagesCollapseAuth">
                                        Authentication
                                        <div class="sb-sidenav-collapse-arrow"><svg class="svg-inline--fa fa-angle-down fa-w-10" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="angle-down" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512" data-fa-i2svg=""><path fill="currentColor" d="M143 352.3L7 216.3c-9.4-9.4-9.4-24.6 0-33.9l22.6-22.6c9.4-9.4 24.6-9.4 33.9 0l96.4 96.4 96.4-96.4c9.4-9.4 24.6-9.4 33.9 0l22.6 22.6c9.4 9.4 9.4 24.6 0 33.9l-136 136c-9.2 9.4-24.4 9.4-33.8 0z"></path></svg><!-- <i class="fas fa-angle-down"></i> Font Awesome fontawesome.com --></div>
                                    </a>
                                    <div class="collapse" id="pagesCollapseAuth" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages" style="">
                                        <nav class="sb-sidenav-menu-nested nav">
                                            <a class="nav-link" href="login.html">Login</a>
                                            <a class="nav-link" href="register.html">Register</a>
                                            <a class="nav-link" href="password.html">Forgot Password</a>
                                        </nav>
                                    </div>
                                    <a class="nav-link" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseError" aria-expanded="false" aria-controls="pagesCollapseError">
                                        Error
                                        <div class="sb-sidenav-collapse-arrow"><svg class="svg-inline--fa fa-angle-down fa-w-10" aria-hidden="false" focusable="false" data-prefix="fas" data-icon="angle-down" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512" data-fa-i2svg=""><path fill="currentColor" d="M143 352.3L7 216.3c-9.4-9.4-9.4-24.6 0-33.9l22.6-22.6c9.4-9.4 24.6-9.4 33.9 0l96.4 96.4 96.4-96.4c9.4-9.4 24.6-9.4 33.9 0l22.6 22.6c9.4 9.4 9.4 24.6 0 33.9l-136 136c-9.2 9.4-24.4 9.4-33.8 0z"></path></svg><!-- <i class="fas fa-angle-down"></i> Font Awesome fontawesome.com --></div>
                                    </a>
                                    <div class="collapse" id="pagesCollapseError" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages" style="">
                                        <nav class="sb-sidenav-menu-nested nav">
                                            <a class="nav-link" href="401.html">401 Page</a>
                                            <a class="nav-link" href="404.html">404 Page</a>
                                            <a class="nav-link" href="500.html">500 Page</a>
                                        </nav>
                                    </div>
                                </nav>
                            </div>
                        <!--  -->
                       
                            <div class="sb-sidenav-menu-heading">Addons</div>
                            <div class="nav-link" onclick="change2()">
                                Test3
                            </div>
                            <div class="nav-link" onclick="change3()">
                                Test4
                            </div>
                            
                        </div>
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
            <main style="height: 100%; width: 100%;">
				<div id="supplyVue"  style="height: 100%; width: 100%;">
              		<template v-if="display[0].show">
              			<div v-if="modal.show" style="height: 100%; width: calc( 100% - 225px ); background: rgba(0, 0, 0, 0.5); position: absolute; padding: 20px; z-index: 2;">
							<div style="width:70%; max-height:80%; background: #fff; transform:translate(-50%,-50%);
							border-radius: 10px; padding: 20px; z-index:1; position: absolute; top:50%; left:50%; overflow:auto;">
							<button @click="modalClose()" type="button"
										class="btn btn-dark" style="float: right;">닫기</button>
								<table class="dataTable-table" id="modalTable">
								     <thead>
                                        <tr>
                                            <th style="width: 30%;"><a>상품명</a></th>
                                            <th style="width: 45%;"><a>상품정보</a></th>
                                            <th style="width: 7.5%;"><a>가격</a></th>
                                            <th style="width: 7.5%;"><a>카테고리</a></th>
                                            <th style="width: 10%;"><a>원산지</a></th>                                          
                                        </tr>
                                    </thead>
									<tbody>
										<tr v-for="(item,index) in modalList" @click="insReason(index,item.rd_prcode)">
											<td>{{item.pr_name}}</td>
											<td>{{item.pr_info}}</td>
											<td>{{item.pr_ttprice}}</td>
											<td>{{item.cate_name}}</td>
											<td>{{item.pr_origin}}</td>
										</tr>
									</tbody>
								</table>
								<div style="text-align: center">
									<button class="btn btn-dark" @click="sendAsResponse(modalList[0].rd_recode,'RA','r')">수락</button>
									<button class="btn btn-dark" @click="sendAsResponse(modalList[0].rd_recode,'FF','r')">거절</button>

								</div>
							</div>
						</div>
              		
              		
              			 <div class="container-fluid px-4" style="z-index: 3;">
                    <div class="dataTable-wrapper dataTable-loading no-footer sortable searchable fixed-columns">
                     <div class="dataTable-top">
                        <div class="dataTable-search">
                           <input class="dataTable-input" type="text" placeholder="상품명을 입력해주세요">
                        </div>
                     </div>  
                        <div class="card mb-4">
                           <div class="card-header">반품 요청 리스트</div>
                            <div class="card-body">
                                <table id="datatablesSimple" class="dataTable-table">
                                    <thead>
                                        <tr>
                                            <th style="width: 20%;"><a>고객사</a></th>
                                            <th style="width: 40%;"><a>내용</a></th>
                                            <th style="width: 20%;"><a>정보</a></th>
                                            <th style="width: 20%;" ><a></a></th>                                          
                                        </tr>
                                    </thead>
       
                                    <tbody>
                                        <tr v-for="item in list" @click="getAsDetail(item.re_code,'r')">
                                            <td >{{item.cl_name}}</td>
                                            <td >{{item.word}}</td>
                                            <td >{{item.re_date}}<br>{{item.cl_hp}}</td>
                                            <td style="text-align: center">
                                               <button @click="mroResponseNewProduct(rnp.pr_code, 'PC')"  type="button" class="btn btn-dark">수락</button>
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
              		<template v-if="display[1].show">
            			<div v-if="modal.show" style="height: 100%; width: calc( 100% - 225px ); background: rgba(0, 0, 0, 0.5); position: absolute; padding: 20px; z-index: 2;">
							<div style="width:70%; max-height:80%; background: #fff; transform:translate(-50%,-50%);
							border-radius: 10px; padding: 20px; z-index:1; position: absolute; top:50%; left:50%; overflow:auto;">
							<button @click="modalClose()" type="button"
										class="btn btn-dark" style="float: right;">닫기</button>
								<table class="dataTable-table" id="modalTable">
								     <thead>
                                        <tr>
                                            <th style="width: 30%;"><a>상품명</a></th>
                                            <th style="width: 45%;"><a>상품정보</a></th>
                                            <th style="width: 7.5%;"><a>가격</a></th>
                                            <th style="width: 7.5%;"><a>카테고리</a></th>
                                            <th style="width: 10%;"><a>원산지</a></th>                                          
                                        </tr>
                                    </thead>
									<tbody>
										<tr v-for="(item,index) in modalList" @click="insReason(index,item.rd_prcode)">
											<td>{{item.pr_name}}</td>
											<td>{{item.pr_info}}</td>
											<td>{{item.pr_ttprice}}</td>
											<td>{{item.cate_name}}</td>
											<td>{{item.pr_origin}}</td>
										</tr>
									</tbody>
								</table>
								<div style="text-align: center">
									<button class="btn btn-dark" @click="sendAsResponse(modalList[0].rd_recode,'EA','e')">수락</button>
									<button class="btn btn-dark" @click="sendAsResponse(modalList[0].rd_recode,'EF','e')">거절</button>

								</div>
							</div>
						</div>
              			 			 <div class="container-fluid px-4">
                    <div class="dataTable-wrapper dataTable-loading no-footer sortable searchable fixed-columns">
                     <div class="dataTable-top">
                        <div class="dataTable-search">
                           <input class="dataTable-input" type="text" placeholder="상품명을 입력해주세요">
                        </div>
                     </div>  
                        <div class="card mb-4">
                           <div class="card-header">교환 요청 리스트</div>
                            <div class="card-body">
                                <table id="datatablesSimple" class="dataTable-table">
                                    <thead>
                                        <tr>
                                            <th style="width: 20%;"><a>고객사</a></th>
                                            <th style="width: 40%;"><a>내용</a></th>
                                            <th style="width: 20%;"><a>정보</a></th>
                                            <th style="width: 20%;" ><a></a></th>                                               
                                        </tr>
                                    </thead>
       
                                    <tbody>
                                        <tr v-for="item in list" @click="getAsDetail(item.re_code,'e')">
                                            <td >{{item.cl_name}}</td>
                                            <td >{{item.word}}</td>
                                            <td >{{item.re_date}}<br>{{item.cl_hp}}</td>
                                            <td style="text-align: center">
                                               <button @click="mroResponseNewProduct(rnp.pr_code, 'PC')"  type="button" class="btn btn-dark">수락</button>
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
              		          <!-- RE_CODE,RE_CLCODE,RE_DATE,CL_NAME,CL_HP,RE_ORIGIN -->   		
              	</div>
             </main>
             
<!--            
            <main style="height: 100%; width: 100%;">
              	<div id="supplyVue"  style="height: 100%; width: 100%; " >
              	
              	<div v-if="page[1].show" style="height: 100%; width: 100%; background: rgba(0,0,0,0.5); position: absolute; padding: 20px; z-index: 2;">
                  <div style="max-width: 100%; width: 83.5%; display: table; background: #fff; border-radius: 10px; padding: 20px; z-index: 1;">
                  <table id="datatablesSimple" class="dataTable-table">
                              <thead>
                              <h5>주문코드 : {{orderDetail[0].rd_recode}}의 상세내역</h5>
                                 <tr>
                                    
                                    <th data-sortable style="width: 12.3333%; background-color: #E0E0E0;"><a>상품이미지</a></th>                                    
                                    <th data-sortable style="width: 10.3333%; background-color: #E0E0E0;"><a>상품코드</a></th>
                                    <th data-sortable style="width: 20%; background-color: #E0E0E0;"><a>상품명</a></th>
                                    <th data-sortable style="width: 10.3333%; background-color: #E0E0E0;"><a>상품수량</a></th>
                                    
                                    <th data-sortable style="width: 10.3333%; background-color: #E0E0E0;"><a>가격(세금)</a></th>                                   
                                    <th data-sortable style="width: 10.3333%; background-color: #E0E0E0;"><a>총 가격</a></th>
                                    <th data-sortable style="width: 13.3333%; background-color: #E0E0E0;"><a>상태코드</a></th>
                                 </tr>
                              </thead>
                              <tbody>
                                 <tr v-for="li in orderDetail" >

                                    <td><img :src="li.pr_image" width="70%" height="70%" alt="no search image"></td>
                                    <td>{{li.rd_prcode}}</td>
                                    <td>{{li.pr_name}}</td>
                                    <td>{{li.rd_quantity}}</td>                                    
                                    <td>{{li.pr_price}}원<div>({{li.pr_tax}}원)</div></td>
                                    <td>{{li.pr_ttprice}}원</td>
                                    <td>{{li.rd_stcode}}</td>
                                 </tr>                                                                                                                                                                                                                                                                                                                          
                                  </tbody>                                                                                                                                              
                  </table>
                  <div v-for="od in orderDetail" v-text="">총 가격 : {{}}</div>
                  <div @click="respond(orderDetail[0].rd_recode)" class="align-top ms-1 btn btn-outline-dark btn-sm" style="position:relative; top:50%;left:42%;">접수확인</div>
                  <div class="align-top ms-1 btn btn-outline-primary btn-sm" @click="close(1)" style="position:relative; top:50%;left:43%;">Close</div>                     
                 </div>
               </div>
              		<template v-if="page[0].show" style="z-index: 3;">
              		<h1 class="mt-4">수주 대기목록</h1>
              				<ol class="breadcrumb mb-4">
								<li class="breadcrumb-item"><a href="/">메인페이지</a></li>
								<li class="breadcrumb-item active">수주대기 목록</li>
							</ol>
							<div class="dataTable-wrapper dataTable-loading no-footer sortable searchable fixed-columns">
								<div class="dataTable-top">
									<div class="dataTable-dropdown">
										<span class="me-2" id="categoryBulkAction">
											<select name="categoryBulkAction" class="d-inline w-auto form-select form-select-sm">
												<option>Bulk Actions</option>
												<option>Delete</option>
											</select>
											<button type="button" class="align-top ms-1 btn btn-outline-dark btn-sm">Apply</button>
										</span>
									<label>
										<select class="dataTable-selector form-select form-select-sm">
											<option value="5">5</option>
											<option value="10" selected="">10</option>
											<option value="15">15</option>
											<option value="20">20</option>
											<option value="25">25</option>
										</select> entries per page
									</label>
								</div>
							<div class="dataTable-search">
								<input class="dataTable-input form-control form-control-sm" placeholder="Search..." type="text">
							</div></div>
							
						<div class="dataTable-container border-0">
							<table class="mb-0 table table-hover dataTable-table">
								<thead>
									<tr class="title">
										<th data-sortable="false" style="width: 14.7704%;">ORDER ID</th>
										<th data-sortable="" style="width: 27.7982%;"><a href="#" class="dataTable-sorter">NAME</a></th>
										<th data-sortable="" style="width: 11.9491%;"><a href="#" class="dataTable-sorter">CONTACT</a></th>
										<th data-sortable="" style="width: 11.9491%;" class="desc"><a href="#" class="dataTable-sorter">DATE</a></th>
										<th data-sortable="" style="width: 16.2598%;"><a href="#" class="dataTable-sorter">TOTAL PRICE</a></th>
										<th data-sortable="" style="width: 12.281%;"><a href="#" class="dataTable-sorter">STATE</a></th>
										
									</tr>
								</thead>
								
								<tbody>
									<tr class="align-middle"  v-for="list in orderList" >
										<td>
											<div class="form-check" >
												<input type="checkbox" class="form-check-input"><label title="" class="form-check-label" >{{list.re_code}}</label>
											</div>
										</td>
										<td @click="data(list.re_code)">
										
												<strong >{{list.cl_name}}</strong>
												<br>
												<span class="text-muted text-sm">{{list.re_clcode}}</span>
											
										</td>
										<td>{{list.cl_hp}}</td>
										<td>{{list.re_date}}</td>
										<td></td>
										<td>
											<span class="badge text-warning bg-warning-light">{{list.re_state}}</span>
										</td>
										
									</tr>
								</tbody>
							</table>
						</div>
						
						<div class="dataTable-bottom">
							<div class="dataTable-info">Showing 1 to 10 of 100 entries</div>
								<nav class="dataTable-pagination">
									<ul class="dataTable-pagination-list">
										<li class="active"><a href="#" data-page="1">1</a></li>
										<li class=""><a href="#" data-page="2">2</a></li>
										<li class=""><a href="#" data-page="3">3</a></li>
										<li class=""><a href="#" data-page="4">4</a></li>
										<li class=""><a href="#" data-page="5">5</a></li>
										<li class=""><a href="#" data-page="6">6</a></li>
										<li class=""><a href="#" data-page="7">7</a></li>
										<li class="ellipsis"><a href="#">…</a></li>
										<li class=""><a href="#" data-page="10">10</a></li>
										<li class="pager"><a href="#" data-page="2">›</a></li>
									</ul>
								</nav>
							</div> 
					 </div>					
              		</template>
              		
              		<div v-if="page[3].show" style="height: 100%; width: 100%; background: rgba(0,0,0,0.5); position: absolute; padding: 20px; z-index: 2;">
                  <div style="max-width: 100%; width: 83.5%; display: table; background: #fff; border-radius: 10px; padding: 20px; z-index: 1;">
                  <table id="datatablesSimple" class="dataTable-table">
                              <thead>
                              <h5>주문코드 : {{deliveryDetail[0].rd_recode}}의 상세내역</h5>
                                 <tr>
                                    
                                    <th data-sortable style="width: 12.3333%; background-color: #E0E0E0;"><a>상품이미지</a></th>                                    
                                    <th data-sortable style="width: 10.3333%; background-color: #E0E0E0;"><a>상품코드</a></th>
                                    <th data-sortable style="width: 20%; background-color: #E0E0E0;"><a>상품명</a></th>
                                    <th data-sortable style="width: 10.3333%; background-color: #E0E0E0;"><a>상품수량</a></th>
                                    
                                    <th data-sortable style="width: 10.3333%; background-color: #E0E0E0;"><a>가격(세금)</a></th>                                   
                                    <th data-sortable style="width: 10.3333%; background-color: #E0E0E0;"><a>총 가격</a></th>
                                    <th data-sortable style="width: 13.3333%; background-color: #E0E0E0;"><a>상태코드</a></th>
                                 </tr>
                              </thead>
                              <tbody>
                                 <tr v-for="li2 in deliveryDetail" >

                                    <td><img :src="li2.pr_image" width="70%" height="70%" alt="no search image"/></td>
                                    <td>{{li2.rd_prcode}}</td>
                                    <td>{{li2.pr_name}}</td>
                                    <td>{{li2.rd_quantity}}</td>                                    
                                    <td>{{li2.pr_price}}원<div>({{li2.pr_tax}}원)</div></td>
                                    <td>{{li2.pr_ttprice}}원</td>
                                    <td>{{li2.rd_stcode}}</td>
                                 </tr>                     
                                     
                                                                                                                                                                                                                                                                 
                                  </tbody>                                                                                                                                              
                  </table>
                  <div @click="respond2(deliveryDetail[0].rd_recode)" class="align-top ms-1 btn btn-outline-dark btn-sm" style="position:relative; top:50%;left:42%;">출고</div>
                  <div class="align-top ms-1 btn btn-outline-primary btn-sm" @click="close(3)" style="position:relative; top:50%;left:43%;">Close</div>                     
                 </div>
               </div>
              		
             		
              		<template v-if="page[2].show" >
              			<h1 class="mt-4">접수완료 목록</h1>
              				<ol class="breadcrumb mb-4">
								<li class="breadcrumb-item"><a href="/">메인페이지</a></li>
								<li class="breadcrumb-item active">접수완료 목록</li>
							</ol>
							<div class="dataTable-wrapper dataTable-loading no-footer sortable searchable fixed-columns">
								<div class="dataTable-top">
									<div class="dataTable-dropdown">
										<span class="me-2" id="categoryBulkAction">
											<select name="categoryBulkAction" class="d-inline w-auto form-select form-select-sm">
												<option>Bulk Actions</option>
												<option>Delete</option>
											</select>
											<button type="button" class="align-top ms-1 btn btn-outline-dark btn-sm">Apply</button>
										</span>
									<label>
										<select class="dataTable-selector form-select form-select-sm">
											<option value="5">5</option>
											<option value="10" selected="">10</option>
											<option value="15">15</option>
											<option value="20">20</option>
											<option value="25">25</option>
										</select> entries per page
									</label>
								</div>
							<div class="dataTable-search">
								<input class="dataTable-input form-control form-control-sm" placeholder="Search..." type="text">
							</div></div>
							
						<div class="dataTable-container border-0">
							<table class="mb-0 table table-hover dataTable-table">
								<thead>
									<tr class="title">
										<th data-sortable="false" style="width: 14.7704%;">ORDER ID</th>
										<th data-sortable="" style="width: 27.7982%;"><a href="#" class="dataTable-sorter">NAME</a></th>
										<th data-sortable="" style="width: 11.9491%;"><a href="#" class="dataTable-sorter">CONTACT</a></th>
										<th data-sortable="" style="width: 11.9491%;" class="desc"><a href="#" class="dataTable-sorter">DATE</a></th>
										<th data-sortable="" style="width: 16.2598%;"><a href="#" class="dataTable-sorter">TOTAL PRICE</a></th>
										<th data-sortable="" style="width: 12.281%;"><a href="#" class="dataTable-sorter">STATE</a></th>
										
									</tr>
								</thead>
								
								<tbody>
									<tr class="align-middle"  v-for="list in deliveryList" >
										<td>
											<div class="form-check" >
												<input type="checkbox" class="form-check-input"><label title="" class="form-check-label" >{{list.re_code}}</label>
											</div>
										</td>
										<td @click="data2(list.re_code)">
										
												<strong >{{list.cl_name}}</strong>
												<br>
												<span class="text-muted text-sm">{{list.re_clcode}}</span>
											
										</td>
										<td>{{list.cl_hp}}</td>
										<td>{{list.re_date}}</td>
										<td></td>
										<td>
											<span class="badge text-warning bg-warning-light">{{list.re_state}}</span>
										</td>
										
									</tr>
								</tbody>
							</table>
						</div>
						
						<div class="dataTable-bottom">
							<div class="dataTable-info">Showing 1 to 10 of 100 entries</div>
								<nav class="dataTable-pagination">
									<ul class="dataTable-pagination-list">
										<li class="active"><a href="#" data-page="1">1</a></li>
										<li class=""><a href="#" data-page="2">2</a></li>
										<li class=""><a href="#" data-page="3">3</a></li>
										<li class=""><a href="#" data-page="4">4</a></li>
										<li class=""><a href="#" data-page="5">5</a></li>
										<li class=""><a href="#" data-page="6">6</a></li>
										<li class=""><a href="#" data-page="7">7</a></li>
										<li class="ellipsis"><a href="#">…</a></li>
										<li class=""><a href="#" data-page="10">10</a></li>
										<li class="pager"><a href="#" data-page="2">›</a></li>
									</ul>
								</nav>
							</div> 
					 </div>					
              		</template>
              			
              	</div>
             </main>
             -->
        </div>
        <!-- <component v-bind:is="currentView" v-bind:aaqqd="mssg"></component> -->
        <script src="${pageContext.request.contextPath}/resources/js/scripts.js"></script>  
        <script src="${pageContext.request.contextPath}/resources/js/datatables-simple-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
		<script src="${pageContext.request.contextPath}/resources/vue/vue.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/js.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/vuecjh.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/hsm.js"></script>
		<%-- <script src="${pageContext.request.contextPath}/resources/js/supplyIYJ.js"></script> --%>
        
    </body>
</html>
