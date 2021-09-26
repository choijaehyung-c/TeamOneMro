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
        <script src = "https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.bundle.js"></script>
		<script src = "https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.bundle.min.js"></script>
		<script src = "https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.js"></script>
		<script src = "https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>
    </head>
    
    <body class="sb-nav-fixed" onLoad="mainPage()">
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
                            <div class="nav-link" onclick="mainPage()">
                                
                                Dashboard
                            </div>
                            <div class="sb-sidenav-menu-heading">Interface</div>
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
                            
                  <div class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                     data-bs-target="#collapsePages" aria-expanded="false"
                     aria-controls="collapsePages">

                     수주관리
                     <div class="sb-sidenav-collapse-arrow">
                        <i class="fas fa-angle-down"></i>
                     </div>
                  </div>
                  <div class="collapse" id="collapsePages"
                     aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                     <nav class="sb-sidenav-menu-nested nav accordion"
                        id="sidenavAccordionPages">
                        <div class="nav-link" onClick="orderWaitList()">수주목록</div>
                        <div class="nav-link" onClick="trackDelivery()">출고목록</div>

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
                            
                             <div class="sb-sidenav-menu-heading">Taxbill</div>
                            <div class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages3" aria-expanded="false" aria-controls="collapsePages3">                                
                                세금계산서
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </div>
                            <div class="collapse" id="collapsePages3" aria-labelledby="headingfour" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">                                
                                    <div class="nav-link" onClick="supplyDealList()">거래내역 조회</div>                          
                                    <div class="nav-link" onClick="supplyIssueTaxbill()">세금계산서 발행</div>
                                    <div class="nav-link" onClick="supplyIssueTaxbillListForm()">세금계산서 내역</div>                 
                                </nav>  
                            </div>

                        <!--  -->
                       
                            
                        </div>
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
            <main style="height: 100%; width: 100%;">
				<div id="supplyVue"  style="height: 100%; width: 100%;">

					<template v-if="display[21].show">
						    <div class="col-lg-6">
							<div class="card mb-4">
								<div class="card-header">
									<svg class="svg-inline--fa fa-chart-bar fa-w-16 me-1"
										aria-hidden="true" focusable="false" data-prefix="fas"
										data-icon="chart-bar" role="img"
										xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"
										data-fa-i2svg="">
										<path fill="currentColor"
											d="M332.8 320h38.4c6.4 0 12.8-6.4 12.8-12.8V172.8c0-6.4-6.4-12.8-12.8-12.8h-38.4c-6.4 0-12.8 6.4-12.8 12.8v134.4c0 6.4 6.4 12.8 12.8 12.8zm96 0h38.4c6.4 0 12.8-6.4 12.8-12.8V76.8c0-6.4-6.4-12.8-12.8-12.8h-38.4c-6.4 0-12.8 6.4-12.8 12.8v230.4c0 6.4 6.4 12.8 12.8 12.8zm-288 0h38.4c6.4 0 12.8-6.4 12.8-12.8v-70.4c0-6.4-6.4-12.8-12.8-12.8h-38.4c-6.4 0-12.8 6.4-12.8 12.8v70.4c0 6.4 6.4 12.8 12.8 12.8zm96 0h38.4c6.4 0 12.8-6.4 12.8-12.8V108.8c0-6.4-6.4-12.8-12.8-12.8h-38.4c-6.4 0-12.8 6.4-12.8 12.8v198.4c0 6.4 6.4 12.8 12.8 12.8zM496 384H64V80c0-8.84-7.16-16-16-16H16C7.16 64 0 71.16 0 80v336c0 17.67 14.33 32 32 32h464c8.84 0 16-7.16 16-16v-32c0-8.84-7.16-16-16-16z"></path></svg>
									<!-- <i class="fas fa-chart-bar me-1"></i> Font Awesome fontawesome.com -->
									베스트 상품 5
								</div>
								<div class="card-body">
									<div class="chartjs-size-monitor">
										<div class="chartjs-size-monitor-expand">
											<div class=""></div>
										</div>
										<div class="chartjs-size-monitor-shrink">
											<div class=""></div>
										</div>
									</div>
									<canvas id="myBarChart" width="1000;" height="361"
										style="display: block; height: 289px; width: 900px;"
										class="chartjs-render-monitor"></canvas>
								</div>
								<div class="card-footer small text-muted">Updated
									yesterday at 11:59 PM</div>
							</div>
						</div>
					</template>
				
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
                                        <tr v-for="item in list">
                                            <td @click="getAsDetail(item.re_code,'r')">{{item.cl_name}}</td>
                                            <td @click="getAsDetail(item.re_code,'r')">{{item.word}}</td>
                                            <td @click="getAsDetail(item.re_code,'r')">{{item.re_date}}<br>{{item.cl_hp}}</td>
                                            <td style="text-align: center">
                                               <button @click="sendAsResponse(item.re_code,'RA','r')" type="button" class="btn btn-dark">수락</button>
                                           <button @click="sendAsResponse(item.re_code,'FF','r')"  type="button" class="btn btn-dark">거절</button>
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
                                        <tr v-for="item in list">
                                            <td @click="getAsDetail(item.re_code,'e')">{{item.cl_name}}</td>
                                            <td @click="getAsDetail(item.re_code,'e')">{{item.word}}</td>
                                            <td @click="getAsDetail(item.re_code,'e')">{{item.re_date}}<br>{{item.cl_hp}}</td>
                                            <td style="text-align: center">
                                           <button @click="sendAsResponse(item.re_code,'EA','e')" type="button" class="btn btn-dark">수락</button>
                                           <button @click="sendAsResponse(item.re_code,'EF','e')"  type="button" class="btn btn-dark">거절</button>
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
<!---------------------------------------------------------------------------------------------------->
 				<template v-if="display[2].show" style="z-index: 3;">
                  <div v-if="modal.show" style="height: 100%; width: calc( 100% - 225px ); background: rgba(0, 0, 0, 0.5); position: absolute; padding: 20px; z-index: 2;">
							<div style="width:84%; max-height:80%; background: #fff; transform:translate(-50%,-50%);
							border-radius: 10px; padding: 20px; z-index:1; position: absolute; top:50%; left:50%; overflow:auto;">
                        <table id="datatablesSimple" class="dataTable-table">
                           <thead>
                              <h5>주문코드 : {{modalDetailList[0].rd_recode}}의 상세내역</h5>
                              <tr>

                                 <th data-sortable
                                    style="width: 12.3333%; background-color: #E0E0E0;"><a>상품이미지</a></th>
                                 <th data-sortable
                                    style="width: 10.3333%; background-color: #E0E0E0;"><a>상품코드</a></th>
                                 <th data-sortable
                                    style="width: 20%; background-color: #E0E0E0;"><a>상품명</a></th>
                                 <th data-sortable
                                    style="width: 10.3333%; background-color: #E0E0E0;"><a>상품수량</a></th>

                                 <th data-sortable
                                    style="width: 10.3333%; background-color: #E0E0E0;"><a>가격(세금)</a></th>
                                 <th data-sortable
                                    style="width: 10.3333%; background-color: #E0E0E0;"><a>총
                                       가격</a></th>
                                 <th data-sortable
                                    style="width: 13.3333%; background-color: #E0E0E0;"><a>상태코드</a></th>
                                 
                              </tr>
                           </thead>
                           <tbody>
                              <tr v-for="(li,index) in modalDetailList" @click="insReason2(index,li.rd_prcode)">

                                 <td>
                                 <div class="form-check">
                                    <input type="checkbox" class="form-check-input" name="choose" @click="getCheckedVal()" :value=li.rd_prcode id="ch">
                                    <label title="" class="form-check-label">
                                          <img :src="li.pr_image" width="70%" height="70%" alt="no search image">
                                    </label>
                                 </div>
                                 </td>
                                 <td>{{li.rd_prcode}}</td>
                                 <td>{{li.pr_name}}</td>
                                 <td>{{li.rd_quantity}}</td>
                                 <td>{{li.pr_price}}원
                                    <div>({{li.pr_tax}}원)</div>
                                 </td>
                                 <td>{{li.pr_ttprice}}원</td>
                                 <td>{{li.rd_stcode}}</td>
                              </tr>
                           </tbody>
                        </table>
                        
                        <div @click="respondOA(modalDetailList[0].rd_recode)"
                           class="align-top ms-1 btn btn-outline-dark btn-sm"
                           style="position: relative; top: 50%; left: 42%;">접수확인</div>
                        <div @click=respondOF(modalDetailList[0].rd_recode)
                           class="align-top ms-1 btn btn-outline-dark btn-sm"
                           style="position: relative; top: 50%; left: 43%;">접수거절</div>
                        <div class="align-top ms-1 btn btn-outline-primary btn-sm"
                           @click="modalClose()"
                           style="position: relative; top: 50%; left: 80%;">Close</div>
                     </div>
                  </div>
                  <h1>수주 목록</h1>
                  <ol class="breadcrumb mb-4">
                     <li class="breadcrumb-item"><a href="/">메인페이지</a></li>
                     <li class="breadcrumb-item active">수주대기 목록</li>
                  </ol>
                  <ol class="breadcrumb mb-4">
   
                   <ul class="mb-5 nav nav-tabs" role="tablist">
                        <li class="nav-item" role="presentation">
                        <button type="button" role="tab" data-rb-event-key="allProducts"
                              aria-selected="true" class="nav-link active" onclick="orderWaitList()">수주대기목록</button>
                        </li>
                        <li class="nav-item" role="presentation">
                           <button type="button" role="tab" data-rb-event-key="archived"
                              aria-selected="false" class="nav-link" onclick="orderRefuseList()">수주거절목록</button></li>
                        <li class="nav-item" role="presentation">
                           <button type="button" role="tab" data-rb-event-key="drafts"
                              aria-selected="false" class="nav-link" onclick="orderReceiveList()">수주완료목록</button></li>
                     </ul>

                  </ol>
                  <div
                     class="dataTable-wrapper dataTable-loading no-footer sortable searchable fixed-columns">
                     <div class="dataTable-top">
                        <div class="dataTable-dropdown">
                           <span class="me-2" id="categoryBulkAction"> <select
                              name="categoryBulkAction"
                              class="d-inline w-auto form-select form-select-sm">
                                 <option>Bulk Actions</option>
                                 <option>Delete</option>
                           </select>
                              <button type="button"
                                 class="align-top ms-1 btn btn-outline-dark btn-sm">Apply</button>
                           </span> <label> <select
                              class="dataTable-selector form-select form-select-sm">
                                 <option value="5">5</option>
                                 <option value="10" selected="">10</option>
                                 <option value="15">15</option>
                                 <option value="20">20</option>
                                 <option value="25">25</option>
                           </select> entries per page
                           </label>
                        </div>
                        <div class="dataTable-search">
                           <input class="dataTable-input form-control form-control-sm"
                              placeholder="Search..." type="text">
                        </div>
                     </div>

                     <div class="dataTable-container border-0">
                        <table class="mb-0 table table-hover dataTable-table">
                           <thead>
                              <tr class="title">
                                 <th data-sortable="false" style="width: 14.7704%;">ORDER
                                    ID</th>
                                 <th data-sortable="" style="width: 27.7982%;"><a
                                    href="#" class="dataTable-sorter">NAME</a></th>
                                 <th data-sortable="" style="width: 11.9491%;"><a
                                    href="#" class="dataTable-sorter">CONTACT</a></th>
                                 <th data-sortable="" style="width: 11.9491%;" class="desc"><a
                                    href="#" class="dataTable-sorter">DATE</a></th>
                                 <th data-sortable="" style="width: 16.2598%;"><a
                                    href="#" class="dataTable-sorter">TOTAL PRICE</a></th>
                                 <th data-sortable="" style="width: 12.281%;"><a href="#"
                                    class="dataTable-sorter">STATE</a></th>

                              </tr>
                           </thead>

                           <tbody>
                              <tr class="align-middle" v-for="li in list" @click="orderListDetail(li.re_code)">
                                 <td>
                                    <div class="form-check">
                                       <input type="checkbox" class="form-check-input"><label
                                          title="" class="form-check-label">{{li.re_code}}</label>
                                    </div>
                                 </td>
                                 <td><strong>{{li.cl_name}}</strong>
                                    <br> <span class="text-muted text-sm">{{li.re_clcode}}</span>

                                 </td>
                                 <td>{{li.cl_hp}}</td>
                                 <td>{{li.re_date}}</td>
                                 <td></td>
                                 <td><span class="badge text-warning bg-warning-light">{{li.re_state}}</span>
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
<!---------------------------------------------------------------------------------------------------->
               <template v-if="display[3].show">
                  <h1>수주 목록</h1>
                  <div v-if="modal.show" style="height: 100%; width: calc( 100% - 225px ); background: rgba(0, 0, 0, 0.5); position: absolute; padding: 20px; z-index: 2;">
							<div style="width:84%; max-height:80%; background: #fff; transform:translate(-50%,-50%);
							border-radius: 10px; padding: 20px; z-index:1; position: absolute; top:50%; left:50%; overflow:auto;">
                        <table id="datatablesSimple" class="dataTable-table">
                           <thead>
                              <h5>주문코드 : {{modalDetailList[0].rd_recode}}의 상세내역</h5>
                              <tr>

                                 <th data-sortable
                                    style="width: 12.3333%; background-color: #E0E0E0;"><a>상품이미지</a></th>
                                 <th data-sortable
                                    style="width: 10.3333%; background-color: #E0E0E0;"><a>상품코드</a></th>
                                 <th data-sortable
                                    style="width: 20%; background-color: #E0E0E0;"><a>상품명</a></th>
                                 <th data-sortable
                                    style="width: 10.3333%; background-color: #E0E0E0;"><a>상품수량</a></th>

                                 <th data-sortable
                                    style="width: 10.3333%; background-color: #E0E0E0;"><a>가격(세금)</a></th>
                                 <th data-sortable
                                    style="width: 10.3333%; background-color: #E0E0E0;"><a>총
                                       가격</a></th>
                                 <th data-sortable
                                    style="width: 13.3333%; background-color: #E0E0E0;"><a>상태코드</a></th>
                              </tr>
                           </thead>
                           <tbody>
                              <tr v-for="li2 in modalDetailList">

                                 <td><img :src="li2.pr_image" width="70%" height="70%"
                                    alt="no search image" /></td>
                                 <td>{{li2.rd_prcode}}</td>
                                 <td>{{li2.pr_name}}</td>
                                 <td>{{li2.rd_quantity}}</td>
                                 <td>{{li2.pr_price}}원
                                    <div>({{li2.pr_tax}}원)</div>
                                 </td>
                                 <td>{{li2.pr_ttprice}}원</td>
                                 <td>{{li2.rd_stcode}}</td>
                              </tr>


                           </tbody>
                        </table>

                        <div class="align-top ms-1 btn btn-outline-primary btn-sm"
                           @click="modalClose()"
                           style="position: relative; top: 50%; left: 43%;">Close</div>
                     </div>
                  </div>
                  <ol class="breadcrumb mb-4">
                     <li class="breadcrumb-item"><a href="/">메인페이지</a></li>
                     <li class="breadcrumb-item active">수주접수완료 목록</li>
                  </ol>
                  <ol class="breadcrumb mb-4">
                     <ul class="mb-5 nav nav-tabs" role="tablist">
                        <li class="nav-item" role="presentation">
                           <button type="button" role="tab"
                              data-rb-event-key="allProducts" aria-selected="false"
                              class="nav-link" onclick="orderWaitList()">수주대기목록</button>
                        </li>
                        <li class="nav-item" role="presentation">
                           <button type="button" role="tab" data-rb-event-key="archived"
                              aria-selected="false" class="nav-link"
                              onclick="orderRefuseList()">수주거절목록</button>
                        </li>
                        <li class="nav-item" role="presentation">
                           <button type="button" role="tab" data-rb-event-key="drafts"
                              aria-selected="true" class="nav-link active"
                              onclick="orderReceiveList()">수주완료목록</button>
                        </li>
                     </ul>
                  </ol>
                  <div
                     class="dataTable-wrapper dataTable-loading no-footer sortable searchable fixed-columns">
                     <div class="dataTable-top">
                        <div class="dataTable-dropdown">
                           <span class="me-2" id="categoryBulkAction"> <select
                              name="categoryBulkAction"
                              class="d-inline w-auto form-select form-select-sm">
                                 <option>Bulk Actions</option>
                                 <option>Delete</option>
                           </select>
                              <button type="button"
                                 class="align-top ms-1 btn btn-outline-dark btn-sm">Apply</button>
                           </span> <label> <select
                              class="dataTable-selector form-select form-select-sm">
                                 <option value="5">5</option>
                                 <option value="10" selected="">10</option>
                                 <option value="15">15</option>
                                 <option value="20">20</option>
                                 <option value="25">25</option>
                           </select> entries per page
                           </label>
                        </div>
                        <div class="dataTable-search">
                           <input id="INPUT" v-on:keyup.13='searchWord()' class="dataTable-input form-control form-control-sm"
                              placeholder="Search..." type="text"/>
                        </div>
                     </div>

                     <div class="dataTable-container border-0">
                        <table class="mb-0 table table-hover dataTable-table">
                           <thead>
                              <tr class="title">
                                 <th data-sortable="false" style="width: 14.7704%;">ORDER
                                    ID</th>
                                 <th data-sortable="" style="width: 27.7982%;"><a
                                    href="#" class="dataTable-sorter">NAME</a></th>
                                 <th data-sortable="" style="width: 11.9491%;"><a
                                    href="#" class="dataTable-sorter">CONTACT</a></th>
                                 <th data-sortable="" style="width: 11.9491%;" class="desc"><a
                                    href="#" class="dataTable-sorter">DATE</a></th>
                                 <th data-sortable="" style="width: 16.2598%;"><a
                                    href="#" class="dataTable-sorter">TOTAL PRICE</a></th>
                                 <th data-sortable="" style="width: 12.281%;"><a href="#"
                                    class="dataTable-sorter">STATE</a></th>

                              </tr>
                           </thead>

                           <tbody>
                              <tr class="align-middle" v-for="li in list" @click="deliveryListDetail(li.re_code)">
                                 <td>
                                    <div class="form-check">
                                       <input type="checkbox" class="form-check-input"><label
                                          title="" class="form-check-label">{{li.re_code}}</label>
                                    </div>
                                 </td>
                                 <td><strong>{{li.cl_name}}</strong>
                                    <br> <span class="text-muted text-sm">{{li.re_clcode}}</span>

                                 </td>
                                 <td>{{li.cl_hp}}</td>
                                 <td>{{li.re_date}}</td>
                                 <td></td>
                                 <td><span class="badge text-warning bg-warning-light">{{li.re_state}}</span>
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
<!---------------------------------------------------------------------------------------------------->
               <template v-if="display[4].show">
                  <h1>수주 목록</h1>

                  <div v-if="modal.show" style="height: 100%; width: calc( 100% - 225px ); background: rgba(0, 0, 0, 0.5); position: absolute; padding: 20px; z-index: 2;">
							<div style="width:84%; max-height:80%; background: #fff; transform:translate(-50%,-50%);
							border-radius: 10px; padding: 20px; z-index:1; position: absolute; top:50%; left:50%; overflow:auto;">
                        <table id="datatablesSimple" class="dataTable-table">
                           <thead>
                              <h5>주문코드 : {{modalDetailList[0].rd_recode}}의 상세내역</h5>
                              <tr>

                                 <th data-sortable
                                    style="width: 12.3333%; background-color: #E0E0E0;"><a>상품이미지</a></th>
                                 <th data-sortable
                                    style="width: 10.3333%; background-color: #E0E0E0;"><a>상품코드</a></th>
                                 <th data-sortable
                                    style="width: 20%; background-color: #E0E0E0;"><a>상품명</a></th>
                                 <th data-sortable
                                    style="width: 10%; background-color: #E0E0E0;"><a>상품수량</a></th>

                                 <th data-sortable
                                    style="width: 10%; background-color: #E0E0E0;"><a>가격(세금)</a></th>
                                 <th data-sortable
                                    style="width: 10.3333%; background-color: #E0E0E0;"><a>총
                                       가격</a></th>
                                 <th data-sortable
                                    style="width: 12.3333%; background-color: #E0E0E0;"><a>상태코드</a></th>
                                 <th data-sortable
                                    style="width: 12.3333%; background-color: #E0E0E0;"><a>사유</a></th>
                              </tr>
                           </thead>
                           <tbody>
                              <tr v-for="li2 in modalDetailList">

                                 <td><img :src="li2.pr_image" width="70%" height="70%"
                                    alt="no search image" /></td>
                                 <td>{{li2.rd_prcode}}</td>
                                 <td>{{li2.pr_name}}</td>
                                 <td>{{li2.rd_quantity}}</td>
                                 <td>{{li2.pr_price}}원
                                    <div>({{li2.pr_tax}}원)</div>
                                 </td>
                                 <td>{{li2.pr_ttprice}}원</td>
                                 <td>{{li2.rd_stcode}}</td>
                                 <td>{{li2.rd_note}}</td>
                              </tr>


                           </tbody>
                        </table>
                        <div class="align-top ms-1 btn btn-outline-primary btn-sm"
                           @click="modalClose()"
                           style="position: relative; top: 50%; left: 45%;">Close</div>
                     </div>
                  </div>
                  <ol class="breadcrumb mb-4">
                     <li class="breadcrumb-item"><a href="/">메인페이지</a></li>
                     <li class="breadcrumb-item active">수주거절 목록</li>
                  </ol>
                  <ol class="breadcrumb mb-4">

                    <ul class="mb-5 nav nav-tabs" role="tablist">
                     <li class="nav-item" role="presentation">
                        <button type="button" role="tab" data-rb-event-key="allProducts"
                           aria-selected="false" class="nav-link"
                           onclick="orderWaitList()">수주대기목록</button>
                     </li>
                     <li class="nav-item" role="presentation">
                        <button type="button" role="tab" data-rb-event-key="archived"
                           aria-selected="true" class="nav-link active"
                           onclick="orderRefuseList()">수주거절목록</button>
                     </li>
                     <li class="nav-item" role="presentation">
                        <button type="button" role="tab" data-rb-event-key="drafts"
                           aria-selected="false" class="nav-link"
                           onclick="orderReceiveList()">수주완료목록</button>
                     </li>
                  </ul>
                  </ol>
                  <div
                     class="dataTable-wrapper dataTable-loading no-footer sortable searchable fixed-columns">
                     <div class="dataTable-top">
                        <div class="dataTable-dropdown">
                           <span class="me-2" id="categoryBulkAction"> <select
                              name="categoryBulkAction"
                              class="d-inline w-auto form-select form-select-sm">
                                 <option>Bulk Actions</option>
                                 <option>Delete</option>
                           </select>
                              <button type="button"
                                 class="align-top ms-1 btn btn-outline-dark btn-sm">Apply</button>
                           </span> <label> <select
                              class="dataTable-selector form-select form-select-sm">
                                 <option value="5">5</option>
                                 <option value="10" selected="">10</option>
                                 <option value="15">15</option>
                                 <option value="20">20</option>
                                 <option value="25">25</option>
                           </select> entries per page
                           </label>
                        </div>
                        <div class="dataTable-search">
                           <input class="dataTable-input form-control form-control-sm"
                              placeholder="Search..." type="text">
                        </div>
                     </div>

                     <div class="dataTable-container border-0">
                        <table class="mb-0 table table-hover dataTable-table">
                           <thead>
                              <tr class="title">
                                 <th data-sortable="false" style="width: 14.7704%;">ORDER
                                    ID</th>
                                 <th data-sortable="" style="width: 27.7982%;"><a
                                    href="#" class="dataTable-sorter">NAME</a></th>
                                 <th data-sortable="" style="width: 11.9491%;"><a
                                    href="#" class="dataTable-sorter">CONTACT</a></th>
                                 <th data-sortable="" style="width: 11.9491%;" class="desc"><a
                                    href="#" class="dataTable-sorter">DATE</a></th>
                                 <th data-sortable="" style="width: 16.2598%;"><a
                                    href="#" class="dataTable-sorter">TOTAL PRICE</a></th>
                                 <th data-sortable="" style="width: 12.281%;"><a href="#"
                                    class="dataTable-sorter">STATE</a></th>

                              </tr>
                           </thead>

                           <tbody>
                              <tr class="align-middle" v-for="li in list" @click="refuseListDetail(li.re_code)">
                                 <td>
                                    <div class="form-check">
                                       <input type="checkbox" class="form-check-input"><label
                                          title="" class="form-check-label">{{li.re_code}}</label>
                                    </div>
                                 </td>
                                 <td><strong>{{li.cl_name}}</strong>
                                    <br> <span class="text-muted text-sm">{{li.re_clcode}}</span>

                                 </td>
                                 <td>{{li.cl_hp}}</td>
                                 <td>{{li.re_date}}</td>
                                 <td></td>
                                 <td><span class="badge text-warning bg-warning-light">{{li.re_state}}</span>
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
<!-- ------------------------------------배송출발목록---------------------------------------------------->
               <template v-if="display[5].show">
                  <h1>배송 목록</h1> <!-- 기사님이 물건을 가져갈때 버튼 누르면, 2로 업데이트 -->
                  <div v-if="modal.show"
                     style="height: 100%; width: 100%; background: rgba(0, 0, 0, 0.5); position: absolute; padding: 20px; z-index: 2;">
                     <div
                        style="max-width: 100%; margin-left:30%; display: table; background: #fff; border-radius: 10px; padding: 20px; z-index: 1;">
                        <table id="datatablesSimple" class="dataTable-table">
                           <thead>
                                 <h5>주문코드 : {{modalDetailList[0].dl_oscode}}배송상태</h5>
                              <tr>
                                 <th>순번</th>
                                 <th>날짜</th>
                                 <th>x위치</th>
                                 <th>x위치</th>
                              </tr>
                           </thead>

                           <tbody>
                              <tr v-for="li in modalDetailList">
                                 <td></td>
                                 <td >{{li.lc_date}}</td>
                                 <td >{{li.lc_x}}</td>
                                 <td >{{li.lc_y}}</td>
                                 
                              </tr>
                           </tbody>
                        </table>

                        <div class="align-top ms-1 btn btn-outline-primary btn-sm"
                           @click="modalClose()"
                           style="position: relative; top: 50%; left: 80%;">Close</div>
                     </div>
                  </div>
                  <ol class="breadcrumb mb-4">
                     <li class="breadcrumb-item"><a href="/">메인페이지</a></li>
                     <li class="breadcrumb-item active">배송중 목록</li>
                  </ol>
                  <div
                     class="dataTable-wrapper dataTable-loading no-footer sortable searchable fixed-columns">
                     <div class="dataTable-top">
                        <div class="dataTable-dropdown">
                           <span class="me-2" id="categoryBulkAction"> <select
                              name="categoryBulkAction"
                              class="d-inline w-auto form-select form-select-sm">
                                 <option>Bulk Actions</option>
                                 <option>Delete</option>
                           </select>
                              <button type="button"
                                 class="align-top ms-1 btn btn-outline-dark btn-sm">Apply</button>
                           </span> <label> <select
                              class="dataTable-selector form-select form-select-sm">
                                 <option value="5">5</option>
                                 <option value="10" selected="">10</option>
                                 <option value="15">15</option>
                                 <option value="20">20</option>
                                 <option value="25">25</option>
                           </select> entries per page
                           </label>
                        </div>
                        <div class="dataTable-search">
                           <input class="dataTable-input form-control form-control-sm"
                              placeholder="Search..." type="text">
                        </div>
                     </div>

                     <div class="dataTable-container border-0">
                        <table class="mb-0 table table-hover dataTable-table">
                           <thead>
                              <tr class="title">
                                 <th data-sortable="false" style="width: 14.7704%;">ORDER
                                    ID</th>
                                 <th data-sortable="" style="width: 11.9491%;" class="desc"><a
                                    href="#" class="dataTable-sorter">DELIVERY CODE</a></th>   
                                 <th data-sortable="" style="width: 27.7982%;"><a
                                    href="#" class="dataTable-sorter">DRIVER NAME</a></th>
                                 <th data-sortable="" style="width: 11.9491%;"><a
                                    href="#" class="dataTable-sorter">CONTACT</a></th>                                 

                                 <th data-sortable="" style="width: 12.281%;"><a href="#"
                                    class="dataTable-sorter">DELIVERY STATE</a></th>

                              </tr>
                           </thead>

                           <tbody>
                              <tr class="align-middle" v-for="li in list"
                                 @click="deliveryState(li.dl_oscode)">
                                 <td>
                                    <div class="form-check">
                                       <input type="checkbox" class="form-check-input"><label
                                          title="" class="form-check-label">{{li.dl_oscode}}</label>
                                    </div>
                                 </td>
                                 <td>{{li.dl_code}}</td>
                                 <td><strong>{{li.dv_name}}</strong> <br> <span
                                    class="text-muted text-sm">{{li.dl_dvcode}}</span></td>
                                 <td>{{li.dv_hp}}</td>
                                 
                                 <td><span class="badge text-warning bg-warning-light">{{li.ds_name}}</span>
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
<!---------------------------------------------------------------------------------------------------->              	
              	<template v-if="display[6].show">	
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
       
                                    <tbody v-if="display[7].show">
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
                                    <tbody  v-if="display[8].show">
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
                                   
                                   <tbody v-if="display[11].show">
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
<!---------------------------------------------------------------------------------------------------->


					<template v-if="display[16].show" style="z-index: 3;">
						<div v-if="modal.show"
							style="height: 100%; width: calc( 100% - 225px ); background: rgba(0, 0, 0, 0.5); position: absolute; padding: 20px; z-index: 2;">
							<div
								style="max-width: 100%; width: 83.5%; display: table; background: #fff; border-radius: 10px; padding: 20px; z-index: 1;">
								<table id="datatablesSimple" class="dataTable-table">
									<thead>
											<tr>
												<th data-sortable style="width: 20%; background-color: #E0E0E0;"><a>고객사코드</a></th>
												<th data-sortable style="width: 20%; background-color: #E0E0E0;"><a>상품명</a></th>
												<th data-sortable style="width: 20%; background-color: #E0E0E0;"><a>공급가액</a></th>
												<th data-sortable style="width: 20%; background-color: #E0E0E0;"><a>세액</a></th>
												<th data-sortable style="width: 20%; background-color: #E0E0E0;"><a>수량</a></th>			
											</tr>
										</thead>
										<tbody>
											<tr v-for="dld in modalDealList" >
												<td>{{dld.rd_recode}}</td>
												<td>{{dld.pr_name}}</td>
												<td>{{dld.pr_price}}</td>
												<td>{{dld.pr_tax}}</td>
												<td>{{dld.rd_quantity}}</td>					
											</tr>							
											    <button class="btn btn-dark"  v-on:click="modalClose()">닫기</button>										  											   																																							
						                </tbody>									  				
						</table>							
					  </div>
					</div>
					</template>
					
<!---------------------------------------------------------------------------------------------------->										
					<template v-if="display[16].show" style="z-index: 3;">
					
						<div class="container-fluid px-4">
							<h1 style = "padding:20px; font-size:25px; color:#808080; font-weight:bold; margin-left:-30px;"><a href="/">메인페이지</a> >거래내역 조회</h1>
						<input type="text" class="form-control" name="word" placeholder="고객사명, 고객사코드 혹은 사업자번호로 검색해주세요." /><span  class="btn btn-secondary btn_two me-2 my-1" type="button" @click="searchDeal()" value="">검 색</span>
							<div class="card mb-4">
								<div class="card-body">※거래내역 확인 [문의 : nsb214@naver.com]</div>
							</div>
							<div class="card mb-4">
								<div class="card-header">
									<i class="fas fa-table me-1"></i> 거래내역
								</div>

								<div class="card-body">
									<table id="datatablesSimple" class="dataTable-table">
										<thead>
											<tr>
												<th data-sortable style="width: 25%;"><a>주문코드</a></th>
												<th data-sortable style="width: 25%;"><a>고객사코드</a></th>
												<th data-sortable style="width: 25%;"><a>고객사명</a></th>
												<th data-sortable style="width: 25%;"><a>날짜</a></th>
											</tr>
										</thead>
										<tbody>
											<tr v-for="dl in list"
												@click="SupplyDealDetail(dl.re_code)">
												<td>{{dl.re_code}}</td>
												<td>{{dl.re_clcode}}</td>
												<td>{{dl.cl_name}}</td>
												<td>{{dl.re_date}}</td>
											</tr>
											</tbody>																	
									</table>
								</div>
							</div>
						</div>
					</template>
					
<!---------------------------------------------------------------------------------------------------->	
					<template v-if="display[17].show" style="z-index: 3;">
					
						<div v-if="modal.show"
							style="height: 100%; width: calc( 100% - 225px ); background: rgba(0, 0, 0, 0.5); position: absolute; padding: 20px; z-index: 2;">
							<div
								style="max-width: 100%; width: 83.5%; display: table; background: #fff; border-radius: 10px; padding: 20px; z-index: 1;">
								<table id="datatablesSimple" class="dataTable-table">
									<thead>
											<tr>
												<th data-sortable style="width: 25%; background-color: #E0E0E0;"><a>고객사코드</a></th>
												<th data-sortable style="width: 25%; background-color: #E0E0E0;"><a>고객사명</a></th>
												<th data-sortable style="width: 25%; background-color: #E0E0E0;"><a>주소</a></th>
												<th data-sortable style="width: 25%; background-color: #E0E0E0;"><a>사업자번호</a></th>
		
											</tr>
										</thead>
										<tbody>
											<tr v-for="cl in modalCLList" @click="inputClientInfo(cl.cl_code)">
												<td>{{cl.cl_code}}</td>
												<td>{{cl.cl_name}}</td>
												<td>{{cl.cl_address}}</td>
												<td>{{cl.cl_corpnum}}</td>
											
											</tr>							
											    <button class="btn btn-dark"  v-on:click="modalClose()">닫기</button>										  											   																																							
						                </tbody>									  				
						</table>							
					  </div>
					</div>
					
					<div v-if="modal2.show"
							style="height: 100%; width: calc( 100% - 225px ); background: rgba(0, 0, 0, 0.5); position: absolute; padding: 20px; z-index: 2;">
							<div
								style="max-width: 100%; width: 83.5%; display: table; background: #fff; border-radius: 10px; padding: 20px; z-index: 1;">
								<table id="datatablesSimple" class="dataTable-table">
									<thead>
											<tr>
												<th data-sortable style="width: 25%; background-color: #E0E0E0;"><a>발주코드</a></th>
												<th data-sortable style="width: 25%; background-color: #E0E0E0;"><a>고객사코드</a></th>
												<th data-sortable style="width: 25%%; background-color: #E0E0E0;"><a>고객사명</a></th>
												<th data-sortable style="width: 25%%; background-color: #E0E0E0;"><a>날짜</a></th>
												
											</tr>
										</thead>
										<tbody>
											<tr v-for="dl in list" @click="inputDeal(dl.re_code)">
												<td>{{dl.re_code}}</td>
												<td>{{dl.re_clcode}}</td>
												<td>{{dl.cl_name}}</td>
												<td>{{dl.re_date}}</td>
												
											</tr>							
											    <button class="btn btn-dark"  v-on:click="modalClose()">닫기</button>										  											   																																							
						                </tbody>									  				
						</table>							
					  </div>
					</div>
					
					
					
						<div class="container-fluid px-4">
							
							<h1 style = "padding:20px; font-size:25px; color:#808080; font-weight:bold; margin-left:-30px;"><a href="/">메인페이지</a> >세금계산서 발행</h1>
						
							<div class="card mb-4">
								<div class="card-body">※[문의 : nsb214@naver.com]</div>
							</div>
							<div class="card mb-4">
								<div class="card-header">
									<i class="fas fa-table me-1"></i> 세금계산서
									
								</div>

								<div>
  
<table width='700' cellpadding='0' cellspacing='0' align='center' class='border_all'>

 <tr>
  <td width='100%'>
    <table cellpadding='0' cellspacing='0' height='65' width='100%'><tr>
      <td rowspan='2' align='center' width='360' class='border_tit'><font size='6'><b>세 금 계 산 서</b></font></td>
      <td rowspan='2' width='5' align='center' class='border_tit'><font size='4'><b>[</b></font></td>
      <td rowspan='2' width='70' align='center' class='border_tit'>공급받는자&nbsp;<br>보 &nbsp;관 &nbsp;용&nbsp;</td>
      <td rowspan='2' width='5' align='center' class='border_tit'><font size='4'><b>]</b></font></td>
     </tr>

    </table>
   </td>
  </tr>
  <tr> 
   <td>
    <table cellpadding='0' cellspacing='0' width='700' >
     <tr>
      <td class='border_up' align='center' width='17' rowspan='5'>공<br><br><br>급<br><br><br>자</td>
      <td class='border_up' align='center' width='55' height='33'>등록번호</td>
      <td class='border_up' align='center' width='278' colspan='5' >{{spbean.sp_corpnum}}</td>
      <td class='border_up' align='center' width='17' rowspan='5'>공<br>급<br>받<br>는<br>자</td>
      <td class='border_up' align='center' width='55'>등록번호</td>
      <td class='border_top' align='center' width='278' colspan='5' v-if="display[18].show" >{{clbean.cl_corpnum}}</td>
      <td class='border_up' align='center' width='55'><button @click="getClientInfo()">찾기</button></td>
     </tr>
     <tr>
      <td class='border_up' align='center' width='55' height='33'>상 호<br>(법인명)</td>
      <td class='border_up' align='center' width='100' colspan='3'>{{spbean.sp_name}}</td>
      <td class='border_up' align='center' width='12' colspan='1'>성<br>명</td>
      <td class='border_up' align='right' width='94' colspan='1'>인</td>
      <td class='border_up' align='center' width='55'>상 호<br>(법인명)</td>
      <td class='border_up' align='center' width='100' colspan='3' v-if="display[18].show" >{{clbean.cl_name}}</td>
      <td class='border_up' align='center' width='12' colspan='1'>성<br>명</td>
      <td class='border_top' align='right' width='94' colspan='2'>인</td>
     </tr>
     <tr>
      <td class='border_up' align='center' width='55' height='33'>사업장<br>주  소</td>
      <td class='border_up' align='center' width='278' colspan='5'>{{spbean.sp_address}}</td>
      <td class='border_up' align='center' width='55'>사업장<br>주  소</td>
      <td class='border_top' align='center' width='278' colspan='6'v-if="display[18].show" >{{clbean.cl_address}}</td>
     </tr>
     <tr>
      <td class='border_up' align='center' width='55' height='33'>업  태</td>
      <td class='border_up' align='center' width='148' colspan='1'>{{spbean.bt_name}}</td>
      <td class='border_up' align='center' width='12' colspan='1'>종<br>목</td>
      <td class='border_up' align='center' width='106' colspan='3'>{{spbean.bk_name}}</td>
      <td class='border_up' align='center' width='55'>업 &nbsp; 태</td>
      <td class='border_up' align='center' width='148' colspan='1' v-if="display[18].show" >{{clbean.bt_name}}</td>
      <td class='border_up' align='center' width='12' colspan='1'>종<br>목</td>
      <td class='border_up' align='center' width='106' colspan='4' v-if="display[18].show" >{{clbean.bk_name}}</td>   
     </tr>
     <tr>
      <td class='border_up' align='center' width='55' height='33' >E-mail</td>
      <td class='border_up' align='center' width='148' colspan='5' >{{spbean.sd_email}}</td>
      <td class='border_up' align='center' width='55' height='33' >E-mail</td>
      <td class='border_up' align='center' width='148' colspan='6' v-if="display[18].show" >{{clbean.cl_email}}</td>
     </tr>
    </table>
   </td>
  </tr>
 
  <tr>
   <td width='100%'>
    <table cellpadding='0' cellspacing='0' width='700' >
      <tr>
      <td class='border_up' align='center' width='195' height='33'>품 &nbsp; &nbsp; &nbsp; 목</td>  
      <td class='border_up' align='center' width='65' height='33'>수 량</td>
      <td class='border_up' align='center' width='150' height='33'>공급가액</td>
      <td class='border_up' align='center' width='83' height='33'>세 액</td>
      <td class='border_up' align='center' width='122' height='33'>합계금액</td>
      </tr>
      
      <tr v-if="display[19].show" v-for="dld in modalDealList" >
      <td class='border_up' align='center' width='195' height='33'>{{dld.pr_name}}</td>      
      <td class='border_up' align='center' width='65' height='33'>{{dld.rd_quantity}}</td>      
      <td class='border_up' align='center' width='150' height='33'>{{dld.pr_price}}</td>      
      <td class='border_up' align='center' width='83' height='33'>{{dld.pr_tax}}</td>
      <td class='border_up' align='center' width='83'height='33'>{{dld.rd_quantity*(dld.pr_tax+dld.pr_price)}}</td>            
      </tr>
      <td><button @click="getTaxDeal()">거래목록</button></td> 
 
    </table>
   </td>
  </tr>
  <tr>
   <td width='100%'>
    <table cellpadding='0' cellspacing='0' width='700' v-if="display[19].show" v-for="dld in modalDealList">
     <tr align='justify'>   
     <td>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</td> 
      <td>{{dld.rd_quantity*(dld.pr_tax+dld.pr_price)}} 이를 영수합니다.<button @click="issueTax(dld.rd_quantity*(dld.pr_tax+dld.pr_price))">발행하기</button></td>
     </tr>

  
    </table>
   </td>
  </tr> 
</table>
								</div>
							</div>
						</div>
					</template>
					
<!---------------------------------------------------------------------------------------------------->						

					
					<template v-if="display[20].show" style="z-index: 3;">

										<div v-if="modal.show"
							style="height: 100%; width: calc( 100% - 225px ); background: rgba(0, 0, 0, 0.5); position: absolute; padding: 20px; z-index: 2;">
							<div
								style="max-width: 100%; width: 83.5%; display: table; background: #fff; border-radius: 10px; padding: 20px; z-index: 1;">
								<table id="datatablesSimple" class="dataTable-table">
									<table width='700' cellpadding='0' cellspacing='0' align='center' class='border_all'>

 <tr>
  <td width='100%'>
    <table cellpadding='0' cellspacing='0' height='65' width='100%'><tr>
      <td rowspan='2' align='center' width='360' class='border_tit'><font size='6'><b>세 금 계 산 서</b></font></td>
      <td rowspan='2' width='5' align='center' class='border_tit'><font size='4'><b>[</b></font></td>
      <td rowspan='2' width='70' align='center' class='border_tit'>공급자&nbsp;<br>보 &nbsp;관 &nbsp;용&nbsp;</td>
      <td rowspan='2' width='5' align='center' class='border_tit'><font size='4'><b>]</b></font></td>
     </tr>

    </table>
   </td>
  </tr>
  <tr> 
   <td>
    <table cellpadding='0' cellspacing='0' width='700' >
     <tr>
      <td class='border_up' align='center' width='17' rowspan='5'>공<br><br><br>급<br><br><br>자</td>
      <td class='border_up' align='center' width='55' height='33'>등록번호</td>
      <td class='border_up' align='center' width='278' colspan='5' >{{tbbean.tb_spcorpnum}}</td>
      <td class='border_up' align='center' width='17' rowspan='5'>공<br>급<br>받<br>는<br>자</td>
      <td class='border_up' align='center' width='55'>등록번호</td>
      <td class='border_top' align='center' width='278' colspan='5' >{{tbbean.tb_clcorpnum}}</td>
      
     </tr>
     <tr>
      <td class='border_up' align='center' width='55' height='33'>상 호<br>(법인명)</td>
      <td class='border_up' align='center' width='100' colspan='3'>{{tbbean.tb_spname}}</td>
      <td class='border_up' align='center' width='12' colspan='1'>성<br>명</td>
      <td class='border_up' align='right' width='94' colspan='1'>인</td>
      <td class='border_up' align='center' width='55'>상 호<br>(법인명)</td>
      <td class='border_up' align='center' width='100' colspan='3'  >{{tbbean.tb_clname}}</td>
      <td class='border_up' align='center' width='12' colspan='1'>성<br>명</td>
      <td class='border_top' align='right' width='94' colspan='2'>인</td>
     </tr>
     <tr>
      <td class='border_up' align='center' width='55' height='33'>사업장<br>주  소</td>
      <td class='border_up' align='center' width='278' colspan='5'>{{tbbean.tb_spaddress}}</td>
      <td class='border_up' align='center' width='55'>사업장<br>주  소</td>
      <td class='border_top' align='center' width='278' colspan='6' >{{tbbean.tb_claddress}}</td>
     </tr>
     <tr>
      <td class='border_up' align='center' width='55' height='33'>업  태</td>
      <td class='border_up' align='center' width='148' colspan='1'>{{tbbean.spbtname}}</td>
      <td class='border_up' align='center' width='12' colspan='1'>종<br>목</td>
      <td class='border_up' align='center' width='106' colspan='3'>{{tbbean.spbkname}}</td>
      <td class='border_up' align='center' width='55'>업 &nbsp; 태</td>
      <td class='border_up' align='center' width='148' colspan='1' >{{tbbean.clbtname}}</td>
      <td class='border_up' align='center' width='12' colspan='1'>종<br>목</td>
      <td class='border_up' align='center' width='106' colspan='4'>{{tbbean.clbkname}}</td>   
     </tr>
     <tr>
      <td class='border_up' align='center' width='55' height='33' >E-mail</td>
      <td class='border_up' align='center' width='148' colspan='5' >{{tbbean.tb_spemail}}</td>
      <td class='border_up' align='center' width='55' height='33' >E-mail</td>
      <td class='border_up' align='center' width='148' colspan='6' >{{tbbean.tb_clemail}}</td>
     </tr>
    </table>
   </td>
  </tr>
  
   <tr>
   <td width='100%'>
    <table cellpadding='0' cellspacing='0' width='700' >
      <tr>
      <td class='border_up' align='center' width='195' height='33'>품 &nbsp; &nbsp; &nbsp; 목</td>  
      <td class='border_up' align='center' width='65' height='33'>수 량</td>
      <td class='border_up' align='center' width='150' height='33'>공급가액</td>
      <td class='border_up' align='center' width='83' height='33'>세 액</td>
      <td class='border_up' align='center' width='122' height='33'>합계금액</td>
      </tr>
      
      <tr>
      <td class='border_up' align='center' width='195' height='33'  v-for="od in od">{{od.pr_name}}</td>      
      <td class='border_up' align='center' width='65' height='33'  v-for="od in od">{{od.od_quantity}}</td>      
      <td class='border_up' align='center' width='150' height='33'  v-for="od in od">{{od.pr_price}}</td>      
      <td class='border_up' align='center' width='83' height='33'  v-for="od in od">{{od.pr_tax}}</td>
      <td class='border_up' align='center' width='83'height='33'  v-for="od in od">{{od.od_quantity*(od.pr_tax+od.pr_price)}}</td>            
      </tr>
 
    </table>
   </td>
  </tr>
 

  <tr>
   <td width='100%'>
    <table cellpadding='0' cellspacing='0' width='700' >
     <tr align='justify'>   
     <td>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</td> 
      <td height='33'>{{tbbean.tb_ttprice}} 이를 영수합니다.</td>
     </tr>

  
    </table>
   </td>
  </tr> 
</table>

<table width='700' cellpadding='0' cellspacing='0' align='center' class='border_all_red'>

 <tr>
  <td width='100%'>
    <table cellpadding='0' cellspacing='0' height='65' width='100%'><tr>
      <td rowspan='2' align='center' width='360' class='border_tit_red'><font size='6'><b>세 금 계 산 서</b></font></td>
      <td rowspan='2' width='5' align='center' class='border_tit_red'><font size='4'><b>[</b></font></td>
      <td rowspan='2' width='70' align='center' class='border_tit_red'>공급받는자&nbsp;<br>보 &nbsp;관 &nbsp;용&nbsp;</td>
      <td rowspan='2' width='5' align='center' class='border_tit_red'><font size='4'><b>]</b></font></td>
     </tr>

    </table>
   </td>
  </tr>
  <tr> 
   <td>
    <table cellpadding='0' cellspacing='0' width='700' >
     <tr>
      <td class='border_up_red' align='center' width='17' rowspan='5'>공<br><br><br>급<br><br><br>자</td>
      <td class='border_up_red' align='center' width='55' height='33'>등록번호</td>
      <td class='border_up_red' align='center' width='278' colspan='5' >{{tbbean.tb_spcorpnum}}</td>
      <td class='border_up_red' align='center' width='17' rowspan='5'>공<br>급<br>받<br>는<br>자</td>
      <td class='border_up_red' align='center' width='55'>등록번호</td>
      <td class='border_top_red' align='center' width='278' colspan='5' >{{tbbean.tb_clcorpnum}}</td>
      
     </tr>
     <tr>
      <td class='border_up_red' align='center' width='55' height='33'>상 호<br>(법인명)</td>
      <td class='border_up_red' align='center' width='100' colspan='3'>{{tbbean.tb_spname}}</td>
      <td class='border_up_red' align='center' width='12' colspan='1'>성<br>명</td>
      <td class='border_up_red' align='right' width='94' colspan='1'>인</td>
      <td class='border_up_red' align='center' width='55'>상 호<br>(법인명)</td>
      <td class='border_up_red' align='center' width='100' colspan='3'  >{{tbbean.tb_clname}}</td>
      <td class='border_up_red' align='center' width='12' colspan='1'>성<br>명</td>
      <td class='border_top_red' align='right' width='94' colspan='2'>인</td>
     </tr>
     
     
     
     
     <tr>
      <td class='border_up_red' align='center' width='55' height='33'>사업장<br>주  소</td>
      <td class='border_up_red' align='center' width='278' colspan='5'>{{tbbean.tb_spaddress}}</td>
      <td class='border_up_red' align='center' width='55'>사업장<br>주  소</td>
      <td class='border_top_red' align='center' width='278' colspan='6' >{{tbbean.tb_claddress}}</td>
     </tr>
     <tr>
      <td class='border_up_red' align='center' width='55' height='33'>업  태</td>
      <td class='border_up_red' align='center' width='148' colspan='1'>{{tbbean.spbtname}}</td>
      <td class='border_up_red' align='center' width='12' colspan='1'>종<br>목</td>
      <td class='border_up_red' align='center' width='106' colspan='3'>{{tbbean.spbkname}}</td>
      <td class='border_up_red' align='center' width='55'>업 &nbsp; 태</td>
      <td class='border_up_red' align='center' width='148' colspan='1' >{{tbbean.clbtname}}</td>
      <td class='border_up_red' align='center' width='12' colspan='1'>종<br>목</td>
      <td class='border_up_red' align='center' width='106' colspan='4'>{{tbbean.clbkname}}</td>   
     </tr>
     <tr>
      <td class='border_up_red' align='center' width='55' height='33' >E-mail</td>
      <td class='border_up_red' align='center' width='148' colspan='5' >{{tbbean.tb_spemail}}</td>
      <td class='border_up_red' align='center' width='55' height='33' >E-mail</td>
      <td class='border_up_red' align='center' width='148' colspan='6' >{{tbbean.tb_clemail}}</td>
     </tr>
    </table>
   </td>
  </tr>
  
   <tr>
   <td width='100%'>
    <table cellpadding='0' cellspacing='0' width='700' >
      <tr>
      <td class='border_up_red' align='center' width='195' height='33'>품 &nbsp; &nbsp; &nbsp; 목</td>  
      <td class='border_up_red' align='center' width='65' height='33'>수 량</td>
      <td class='border_up_red' align='center' width='150' height='33'>공급가액</td>
      <td class='border_up_red' align='center' width='83' height='33'>세 액</td>
      <td class='border_up_red' align='center' width='122' height='33'>합계금액</td>
      </tr>
      
      <tr>
      <td class='border_up_red' align='center' width='195' height='33' v-for="od in od">{{od.pr_name}}</td>      
      <td class='border_up_red' align='center' width='65' height='33' v-for="od in od">{{od.od_quantity}}</td>      
      <td class='border_up_red' align='center' width='150' height='33' v-for="od in od">{{od.pr_price}}</td>      
      <td class='border_up_red' align='center' width='83' height='33' v-for="od in od">{{od.pr_tax}}</td>
      <td class='border_up_red' align='center' width='83'height='33' v-for="od in od">{{od.od_quantity*(od.pr_tax+od.pr_price)}}</td>            
      </tr>
 
    </table>
   </td>
  </tr>

  <tr>
   <td width='100%'>
    <table cellpadding='0' cellspacing='0' width='700' >
     <tr align='justify'>   
     <td>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</td> 
      <td height='33'>{{tbbean.tb_ttprice}} 이를 영수합니다.</td>
     </tr>

  
    </table>
   </td>
  </tr> 
</table>		
															    
<button class="btn btn-dark"  v-on:click="modalClose()">닫기</button>										  											   																																							
						               									  				
						</table>							
					  </div>
					</div>
						<div class="container-fluid px-4">
							<h1 style = "padding:20px; font-size:25px; color:#808080; font-weight:bold; margin-left:-30px;"><a href="/">메인페이지</a> >세금계산서 발행 내역</h1>
						
							<div class="card mb-4">
								<div class="card-body">※세금계산서 발행 내역 확인 [문의 : nsb214@naver.com]</div>
							</div>
							<div class="card mb-4">
								<div class="card-header">
									<i class="fas fa-table me-1"></i> 세금계산서 발행 내역
								</div>

								<div class="card-body">
									<table id="datatablesSimple" class="dataTable-table">
										<thead>
											<tr>
												<th data-sortable style="width: 25%;"><a>세금계산서코드</a></th>
												<th data-sortable style="width: 25%;"><a>주문코드</a></th>
												<th data-sortable style="width: 25%;"><a>고객사코드</a></th>
												<th data-sortable style="width: 25%;"><a>고객사명</a></th>
											</tr>
										</thead>
										<tbody>
											<tr v-for="tl in list"
												@click="taxDetail(tl.tb_code)">
												<td>{{tl.tb_code}}</td>
												<td>{{tl.tb_oscode}}</td>
												<td>{{tl.tb_clcode}}</td>
												<td>{{tl.tb_clname}}</td>
											</tr>
											</tbody>																	
									</table>
								</div>
							</div>
						</div>
					</template>
					<!-- !!!!!!!!!!!!!!!!!!!!!!!!!!!!!승백끝!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
					
					
           		<template v-if="display[9].show">		
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
                                    <thead>
                                        <tr>
                                            <th style="width: 10%;"><a>카테고리</a></th>
                                            <th style="width: 60%;"><a>상품명</a></th>
                                            <th style="width: 20%;"><a>상태</a></th>
                                            <th style="width: 10%;" ><a></a></th>                                          
                                        </tr>
                                    </thead>
       
                                    <tbody v-if="display[12].show">
                                        <tr v-for="PRAF in list">
                                            <td @click="productDetail(PRAF.pr_code, PRAF.pr_stcode)">{{PRAF.cate_name}}</td>
                                            <td @click="productDetail(PRAF.pr_code, PRAF.pr_stcode)">{{PRAF.pr_name}}</td>
                                            <td @click="productDetail(PRAF.pr_code, PRAF.pr_stcode)">{{PRAF.st_name}}</td>
                                            
                                            <td style="text-align: center">
                   								<button @click="supplyRequestCancel(PRAF.pr_code, PRAF.pr_stcode)" type="button" class="btn btn-dark">요청삭제</button>
                   							</td>
                                        </tr>
                                    </tbody>
                                    
                                    <tbody v-if="display[13].show">
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
<!---------------------------------------------------------------------------------------------------->           				
           		<template v-if="display[10].show" style="z-index: 3;">  
           				
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
       
                                    <tbody v-if="display[14].show">
                                        <tr v-for="MRDRDA in list">
                                            <td @click="productDetail(MRDRDA.pr_code, MRDRDA.pr_stcode)">{{MRDRDA.cate_name}}</td>
                                            <td @click="productDetail(MRDRDA.pr_code, MRDRDA.pr_stcode)">{{MRDRDA.pr_name}}</td>
                                            <td @click="productDetail(MRDRDA.pr_code, MRDRDA.pr_stcode)">{{MRDRDA.st_name}}</td>
                                            <td style="text-align: center">
                                            	<button @click="supplyRequestCancel(MRDRDA.pr_code, MRDRDA.pr_stcode)" type="button" class="btn btn-dark">요청삭제</button>
                   							</td>
                                        </tr>
                                    </tbody>
                                    
                                    <tbody v-if="display[15].show">
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
<!---------------------------------------------------------------------------------------------------->              	
              	
              	
              	
              	</div>
             </main>
             
             	<script>
		
		
		function gettingChart(data){
			let data1 = data;
			
			
		var ctx = document.getElementById('myBarChart');
		var myChart = new Chart(ctx, {
			  type: 'bar',
			  data: {
				  
			    labels: [data1[0].pr_name.substring(2,14), data[1].pr_name.substring(5,12), data[2].pr_name.substring(6,16),data[3].pr_name.substring(4,16), data[4].pr_name.substring(0,8)],
			    datasets: [{
			      label: "Revenue",
			      backgroundColor: ["rgb(2,117,216)","rgb(255,99,132)","rgb(54,162,235)","rgb(255,205,86)","rgb(255,100,95)"],
			      borderColor: "rgba(2,117,216,1)",
			      data: [data1[0].rd_quantity,data1[1].rd_quantity,data1[2].rd_quantity,data1[3].rd_quantity,data1[4].rd_quantity],
			    }],
			  },
			  options: {
			    scales: {
			      xAxes: [{
			        time: {
			          unit: 'month'
			        },
			        gridLines: {
			          display: false
			        },
			        ticks: {
			          maxTicksLimit: 6
			        }
			      }],
			      yAxes: [{
			        ticks: {
			          min: 0,
			          max: 1000,
			          maxTicksLimit: 6
			        },
			        gridLines: {
			          display: true
			        }
			      }],
			    },
			    legend: {
			      display: false
			    }
			  }
			});
			
		}


		</script>
      
        </div>
        <!-- <component v-bind:is="currentView" v-bind:aaqqd="mssg"></component> -->
        <script src="${pageContext.request.contextPath}/resources/js/scripts.js"></script>  
        <script src="${pageContext.request.contextPath}/resources/js/datatables-simple-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
		<script src="${pageContext.request.contextPath}/resources/vue/vue.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/js.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/vuecjh.js"></script>
		<%-- <script src="${pageContext.request.contextPath}/resources/js/supplyIYJ.js"></script> --%>
 		<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>

        
    </body>
</html>
