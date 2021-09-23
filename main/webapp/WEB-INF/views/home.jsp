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
            <a class="navbar-brand ps-3" href="/">MRONE<button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><svg class="svg-inline--fa fa-bars fa-w-14" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="bars" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" data-fa-i2svg=""><path fill="currentColor" d="M16 132h416c8.837 0 16-7.163 16-16V76c0-8.837-7.163-16-16-16H16C7.163 60 0 67.163 0 76v40c0 8.837 7.163 16 16 16zm0 160h416c8.837 0 16-7.163 16-16v-40c0-8.837-7.163-16-16-16H16c-8.837 0-16 7.163-16 16v40c0 8.837 7.163 16 16 16zm0 160h416c8.837 0 16-7.163 16-16v-40c0-8.837-7.163-16-16-16H16c-8.837 0-16 7.163-16 16v40c0 8.837 7.163 16 16 16z"></path></svg><!-- <i class="fas fa-bars"></i> Font Awesome fontawesome.com -->
            </button></a>
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
                        	<br>
                            <div class="nav-link" onClick="tttset()">
                                Dashboard
                            </div>
                            <div class="sb-sidenav-menu-heading">Cooperation</div>
                        
                            <div class="nav-link collapsed" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                거래사
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </div>
                            <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <div class="nav-link" onClick="clientList()">고객사 목록</div>
                                    <div class="nav-link" onClick="supplyList()">공급사 목록</div>
                                </nav>
                            </div>
                            <div class="sb-sidenav-menu-heading">Management</div> 
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
                            <div class="sb-sidenav-menu-heading">Order</div>
                            <div class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages3" aria-expanded="false" aria-controls="collapsePages3">                                
                                발주관리
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </div>
                            <div class="collapse" id="collapsePages3" aria-labelledby="headingfour" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">                                
                                    <div class="nav-link" onClick="requestOrder()">발주하기</div>                
                                </nav>  
                            </div>
 
                            
                        </div>
                    </div>
                </nav>
            </div>
		<div id="layoutSidenav_content">
			<main style="height: 100%; width: 100%;">
				<div id="mainVue" style="height: 100%; width: 100%;">
<!------------------------------------------------------------------------------------------------------------------------>
					<template v-if="display[0].show" style="z-index: 3;">
						<!-- !!!!!!!!상품등록신청 모달!!!!!!!!!!!! -->
						<div v-if="modal.show"
							style="height: 100%; width: 100%; background: rgba(0, 0, 0, 0.5); position: absolute; padding: 20px; z-index: 2;">
							<div
								style="max-width: 100%; width: auto; display: table; background: #fff; border-radius: 10px; padding: 20px; z-index: 1;">
								<table class="dataTable-table">

									<img :src="modalDetail.pr_image">
									<tbody>
										<tr>
											<td>회사명</td>
											<td>{{modalDetail.sp_name}}</td>
										</tr>
										<tr>
											<td>전화번호</td>
											<td>{{modalDetail.sp_tel}}</td>
										</tr>
										<tr>
											<td>주소</td>
											<td>{{modalDetail.sp_address}}</td>
										</tr>
										<tr>
											<td>상품명</td>
											<td>{{modalDetail.pr_name}}</td>
										</tr>
										<tr>
											<td>가격</td>
											<td>{{modalDetail.pr_price}}</td>
										</tr>
										<tr>
											<td>수량</td>
											<td>{{modalDetail.pr_stock}}</td>
										</tr>
										<tr>
											<td>원산지</td>
											<td>{{modalDetail.pr_origin}}</td>
										</tr>
										<tr>
											<td>정보</td>
											<td>{{modalDetail.pr_info}}</td>
										</tr>

									</tbody>
								</table>
								<div style="text-align: center">
									<button
										@click="mroResponseNewProduct(modalDetail.pr_code, 'PC')"
										type="button" class="btn btn-dark">등록</button>
									<button
										@click="mroResponseNewProduct(modalDetail.pr_code, 'AF')"
										type="button" class="btn btn-dark">거절</button>
									<button @click="modalClose()" type="button"
										class="btn btn-dark">닫기</button>
								</div>
							</div>
						</div>
						<div class="container-fluid px-4">
						<h6>&nbsp</h6>
							<div
								class="dataTable-wrapper dataTable-loading no-footer sortable searchable fixed-columns">
								<div class="dataTable-top">
									<div class="dataTable-search">
										<input class="dataTable-input" type="text"
											placeholder="상품명을 입력해주세요">
									</div>
								</div>
								<div class="card mb-4">
									<div class="card-header">등록신청</div>
									<div class="card-body">
										<table id="datatablesSimple" class="dataTable-table">
											<thead>
												<tr>
													<th style="width: 15%;"><a>회사명</a></th>
													<th style="width: 15%;"><a>카테고리</a></th>
													<th style="width: 50%;"><a>상품명</a></th>
													<th style="width: 20%;"><a></a></th>
												</tr>
											</thead>

											<tbody>
												<tr v-for="rnp in list">
													<td @click="mroGetNewProductDetailPage(rnp.pr_code)">{{rnp.sp_name}}</td>
													<td @click="mroGetNewProductDetailPage(rnp.pr_code)">{{rnp.cate_name}}</td>
													<td @click="mroGetNewProductDetailPage(rnp.pr_code)">{{rnp.pr_name}}</td>
													<td style="text-align: center">
														<button @click="mroResponseNewProduct(rnp.pr_code, 'PC')"
															type="button" class="btn btn-dark">등록</button>
														<button @click="mroResponseNewProduct(rnp.pr_code, 'AF')"
															type="button" class="btn btn-dark">거절</button>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</template>
<!------------------------------------------------------------------------------------------------------------------------>
					<template v-if="display[1].show" style="z-index: 3;">
						<!-- !!!!!!!!상품수정신청 모달!!!!!!!!!!!! -->
						<div v-if="modal.show"
							style="height: 100%; width: 100%; background: rgba(0, 0, 0, 0.5); position: absolute; padding: 20px; z-index: 2;">
							<div
								style="max-width: 100%; width: auto; display: table; background: #fff; border-radius: 10px; padding: 20px; z-index: 1;">
								<table class="dataTable-table">

									<img :src="modalDetail.pr_image">
									<tbody>

										<tr>
											<td>회사명</td>
											<td>{{modalDetail.sp_name}}</td>
										</tr>
										<tr>
											<td>전화번호</td>
											<td>{{modalDetail.sp_tel}}</td>
										</tr>
										<tr>
											<td>주소</td>
											<td>{{modalDetail.sp_address}}</td>
										</tr>
										<tr>
											<td>상품명</td>
											<td>{{modalDetail.pr_name}}</td>
										</tr>
										<tr>
											<td>가격</td>
											<td>{{modalDetail.pr_price}}</td>
										</tr>
										<tr>
											<td>수량</td>
											<td>{{modalDetail.pr_stock}}</td>
										</tr>
										<tr>
											<td>원산지</td>
											<td>{{modalDetail.pr_origin}}</td>
										</tr>
										<tr>
											<td>정보</td>
											<td>{{modalDetail.pr_info}}</td>
										</tr>

									</tbody>
								</table>
								<div style="text-align: center">
									<button
										@click="mroResponseModifyProduct(modalDetail.pr_code, modalDetail.pr_stcode, '1')"
										type="button" class="btn btn-dark">승인</button>
									<button
										@click="mroResponseModifyProduct(modalDetail.pr_code, modalDetail.pr_stcode, '2')"
										type="button" class="btn btn-dark">거절</button>
									<button @click="modalClose()" type="button"
										class="btn btn-dark">닫기</button>
								</div>
							</div>
						</div>
						<div class="container-fluid px-4">

							<h1 style="padding: 20px">상품 수정신청 리스트</h1>

							<div
								class="dataTable-wrapper dataTable-loading no-footer sortable searchable fixed-columns">
								<div class="dataTable-top">
									<div class="dataTable-search">
										<input class="dataTable-input" type="text"
											placeholder="상품명을 입력해주세요">
									</div>
								</div>
								<div class="card mb-4">
									<div class="card-header">수정요청</div>
									<div class="card-body">
										<table id="datatablesSimple" class="dataTable-table">
											<thead>
												<tr>
													<th style="width: 15%;"><a>회사명</a></th>
													<th style="width: 15%;"><a>카테고리</a></th>
													<th style="width: 50%;"><a>상품명</a></th>
													<th style="width: 20%;"><a></a></th>
												</tr>
											</thead>

											<tbody>
												<tr v-for="rmp in list"
													v-if="rmp.pr_stcode =='MR'">
													<td
														@click="mroGetModifyProductDetailPage(rmp.pr_code, '1')">{{rmp.sp_name}}</td>
													<td
														@click="mroGetModifyProductDetailPage(rmp.pr_code, '1')">{{rmp.cate_name}}</td>
													<td
														@click="mroGetModifyProductDetailPage(rmp.pr_code, '1')">{{rmp.pr_name}}</td>
													<td style="text-align: center">
														<button
															@click="mroResponseModifyProduct(rmp.pr_code, rmp.pr_stcode, '1')"
															type="button" class="btn btn-dark">승인</button>
														<button
															@click="mroResponseModifyProduct(rmp.pr_code, rmp.pr_stcode, '2')"
															type="button" class="btn btn-dark">거절</button>
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
													<th style="width: 15%;"><a>회사명</a></th>
													<th style="width: 15%;"><a>카테고리</a></th>
													<th style="width: 50%;"><a>상품명</a></th>
													<th style="width: 20%;"><a></a></th>
												</tr>
											</thead>

											<tbody>
												<tr v-for="rmp in list"
													v-if="rmp.pr_stcode =='DR'">
													<td
														@click="mroGetModifyProductDetailPage(rmp.pr_code, '2')">{{rmp.sp_name}}</td>
													<td
														@click="mroGetModifyProductDetailPage(rmp.pr_code, '2')">{{rmp.cate_name}}</td>
													<td
														@click="mroGetModifyProductDetailPage(rmp.pr_code, '2')">{{rmp.pr_name}}</td>
													<td style="text-align: center">
														<button
															@click="mroResponseModifyProduct(rmp.pr_code, rmp.pr_stcode, '1')"
															type="button" class="btn btn-dark">승인</button>
														<button
															@click="mroResponseModifyProduct(rmp.pr_code, rmp.pr_stcode, '2')"
															type="button" class="btn btn-dark">거절</button>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</template>	
<!------------------------------------------------------------------------------------------------------------------------>
                	<template v-if="display[2].show">
                		<div class="container-fluid px-4">
							<h1 class="mt-4">공급사 목록</h1>
							<ol class="breadcrumb mb-4">
								<li class="breadcrumb-item"><a href="/">메인페이지</a></li>
								<li class="breadcrumb-item active">공급사 목록</li>
							</ol>
								<input type="text" class="form-control" name="word" placeholder="공급사 이름을 검색하세요." /><span  class="btn btn-secondary btn_two me-2 my-1" type="button" @click="search()" value="">검 색</span>
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
                	
                		<tr v-for="ii in list" ><td  @click="datata(ii.sp_code)">{{ii.sp_name}}</td><td>{{ii.sp_address}}</td><td>{{ii.bk_name}}</td><td ><button  class="btn btn-secondary me-2 my-1"  @click="deleteS(ii.sp_code)">삭 제</button></td></tr>
                		</tbody>
                		
              
                		</table>    
   	
                		</template>               
<!------------------------------------------------------------------------------------------------------------------------>		
                	<template v-if="display[3].show">
                		<div class="container-fluid px-4">
							<h1 class="mt-4">고객사 목록</h1>
							<ol class="breadcrumb mb-4">
								<li class="breadcrumb-item"><a href="/">메인페이지</a></li>
								<li class="breadcrumb-item active">고객사 목록</li>
							</ol>
								<input type="text" class="form-control" name="wordC" placeholder="고객사 이름을 검색하세요." /><span  class="btn btn-secondary btn_two me-2 my-1" type="button" @click="searchClient()" value="">검 색</span>
								<div class="btn btn-secondary me-2 my-1" type="button" id="insSp" data-bs-toggle="modal" data-bs-target="#ModalLg">새 고객사 등록</div>
								                		<div class="modal fade" id="ModalLg" tabindex="-1" aria-labelledby="myLargeModalLabel" style="display: none;" aria-hidden="true">
                                                        <div class="modal-dialog modal-lg" role="document">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title" >새 고객사 등록</h5>
                                                                    <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close" onClick="close()"></button>
                                                                </div>
                                                                <div class="modal-body">
                                                                 	<table><tr class='title'><th scope='row'><span class='title_d'>업체명</span></th><td colspan='2'><input type="text" class="text" id='subject' name="cl_name" value=""></tr>
                                                                 			<tr class='title'><th scope='row'><span class='title_d'>업체코드</span></th><td colspan='2'><input type="text" class="text" id='subject' name="cl_code" value=""></tr>
                                                                 			<tr class='title'><th scope='row'><span class='title_d'>임시비밀번호</span></th><td colspan='2'><input type="password" class="text" id='subject' name="cl_pwd" value=""></tr>
                                                                 			<tr class='title'><th scope='row'><span class='title_d'>지부장</span></th><td colspan='2'><input type="text" class="text" id='subject' name="cl_ceo" value=""></tr>
                                                                 			<tr class='title'><th scope='row'><span class='title_d'>주소</span></th><td colspan='2'><input type="text" class="text" id='subject' name="cl_address" value=""></tr>
                                                                 			<tr class='title'><th scope='row'><span class='title_d'>연락처</span></th><td colspan='2'><input type="text" class="text" id='subject' name="cl_hp" value=""></tr>
                                                                 			<tr class='title'><th scope='row'><span class='title_d'>사업자번호</span></th><td colspan='2'><input type="text" class="text" id='subject' name="cl_corpnum" value=""></tr>
                                           
                                                                 			<tr class='title'><th scope='row'><span class='title_d'>업태</span></th><td colspan='2'><select name="cl_btype"><option value='C'>제조업</option><option value='D'>유통업</option><option value='G'>도매업</option></select>
                                                                 			<tr class='title'><th scope='row'><span class='title_d'>업종</span></th><td colspan='2'><select name="cl_bkind"><option value='KS'>사무용품</option><option value='KB'>식음료</option><option value='KL'>생활용품</option><option value='KC'>청소용품</option></select>                                                                
                                                                 	</table>                        
                                                                </div>
                                                                <div class="modal-footer">
                                                                <button class="btn btn-primary" type="button" data-bs-dismiss="modal" onClick="close()">Close</button>
                                                                <button class="btn btn-primary" type="button" @click="addC()">Add</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="card mb-4">
								<div class="card-header">
									<i class="fas fa-table me-1"></i> 고객사 목록
								</div>  
								<div class="card-body">            		               		
                		<table id="datatablesSimple"class="dataTable-table"><tr><thead><th>이름</th><th>위치</th><th >업종</th><th>요청</th></tr></thead>
                		<tbody>
                	
                		<tr v-for="ii in list" ><td>{{ii.cl_name}}</td><td>{{ii.cl_address}}</td><td>{{ii.bk_name}}</td><td><button  class="btn btn-secondary me-2 my-1" @click="deleteC(ii.cl_code)">삭 제</button></td></tr>
                		</tbody>
                		
         
                		</table>    
   	
                		</template>     
<!------------------------------------------------------------------------------------------------------------------------>
					<template v-if="display[4].show" style="z-index: 3;">
						<div v-if="modal.show"
							style="height: 100%; width: 100%; background: rgba(0, 0, 0, 0.5); position: absolute; padding: 20px; z-index: 2;">
							<div
								style="max-width: 100%; width: 83.5%; display: table; background: #fff; border-radius: 10px; padding: 20px; z-index: 1;">
								<table id="datatablesSimple" class="dataTable-table">
									<thead>
										<tr>
											<th data-sortable
												style="width: 13.3333%; background-color: #E0E0E0;"><a>공급사코드</a></th>
											<th data-sortable
												style="width: 13.3333%; background-color: #E0E0E0;"><a>공급사명</a></th>
											<th data-sortable
												style="width: 13.3333%; background-color: #E0E0E0;"><a>주문코드</a></th>
											<th data-sortable
												style="width: 13.3333%; background-color: #E0E0E0;"><a>상품코드</a></th>
											<th data-sortable
												style="width: 20%; background-color: #E0E0E0;"><a>상품명</a></th>
											<th data-sortable
												style="width: 13.3333%; background-color: #E0E0E0;"><a>상품수량</a></th>
											<th data-sortable
												style="width: 13.3333%; background-color: #E0E0E0;"><a>상태코드</a></th>
										</tr>
									</thead>
									<tbody>
										<tr v-for="old in modalDetailList">
											<td>{{old.od_prspcode}}</td>
											<td>{{old.sp_name}}</td>
											<td>{{old.od_oscode}}</td>
											<td>{{old.od_prcode}}</td>
											<td>{{old.pr_name}}</td>
											<td>{{old.od_quantity}}</td>
											<td>{{old.od_stcode}}</td>
										</tr>
										<button class="btn-datatable" v-on:click="modalClose()">
											<img src="../../resources/img/close.png">
										</button>
									</tbody>
								</table>
							</div>
						</div>
						<div class="container-fluid px-4">
							<h1
								style="padding: 20px; font-size: 25px; color: #808080; font-weight: bold; margin-left: -30px;">
								<a href="/">메인페이지</a> >주문목록
							</h1>

							<div class="card mb-4">
								<div class="card-body">※주문목록 확인 후 처리해주세요 [문의 :
									nsb214@naver.com]</div>
							</div>
							<div class="card mb-4">
								<div class="card-header">
									<i class="fas fa-table me-1"></i> 주문 접수 목록
								</div>

								<div class="card-body">
									<table id="datatablesSimple" class="dataTable-table">
										<thead>
											<tr>
												<th data-sortable style="width: 19.5444%;"><a>주문코드</a></th>
												<th data-sortable style="width: 19.5444%;"><a>고객사명</a></th>
												<th data-sortable style="width: 19.5444%;"><a>접수날짜</a></th>
												<th data-sortable style="width: 19.5444%;"><a>접수내용</a></th>
											</tr>
										</thead>
										<tbody>
											<tr v-for="ol in list"
												@click="mroOrderListDetail(ol.os_code)">
												<td>{{ol.os_code}}</td>
												<td>{{ol.cl_name}}</td>
												<td>{{ol.os_date}}</td>
												<td>{{ol.os_state}}</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</template>
<!------------------------------------------------------------------------------------------------------------------------>
					<template v-if="display[5].show" style="z-index: 3;">
						<div v-if="modal.show"
							style="height: 100%; width: 100%; background: rgba(0, 0, 0, 0.5); position: absolute; padding: 20px; z-index: 2;">
							<div
								style="max-width: 100%; width: 83.5%; display: table; background: #fff; border-radius: 10px; padding: 20px; z-index: 1;">
								<table id="datatablesSimple" class="dataTable-table">
									<thead>
										<tr>
											<th data-sortable
												style="width: 13.3333%; background-color: #E0E0E0;"><a>공급사코드</a></th>
											<th data-sortable
												style="width: 13.3333%; background-color: #E0E0E0;"><a>공급사명</a></th>
											<th data-sortable
												style="width: 13.3333%; background-color: #E0E0E0;"><a>주문코드</a></th>
											<th data-sortable
												style="width: 13.3333%; background-color: #E0E0E0;"><a>상품코드</a></th>
											<th data-sortable
												style="width: 20%; background-color: #E0E0E0;"><a>상품명</a></th>
											<th data-sortable
												style="width: 13.3333%; background-color: #E0E0E0;"><a>상품수량</a></th>
											<th data-sortable
												style="width: 13.3333%; background-color: #E0E0E0;"><a>상태코드</a></th>
										</tr>
									</thead>
									<tbody>
										<tr v-for="old in modalDetailList">
											<td>{{old.od_prspcode}}</td>
											<td>{{old.sp_name}}</td>
											<td>{{old.od_oscode}}</td>
											<td>{{old.od_prcode}}</td>
											<td>{{old.pr_name}}</td>
											<td>{{old.od_quantity}}</td>
											<td>{{old.od_stcode}}</td>
										</tr>
										<button class="btn-datatable" v-on:click="modalClose()">
											<img src="../../resources/img/close.png">
										</button>
									</tbody>
								</table>
							</div>
						</div>
						<div class="container-fluid px-4">
							<h1
								style="padding: 20px; font-size: 25px; color: #808080; font-weight: bold; margin-left: -30px;">
								<a href="/">메인페이지</a> >반품신청목록
							</h1>

							<div class="card mb-4">
								<div class="card-body">※반품신청목록 확인 후 처리해주세요 [문의 :
									nsb214@naver.com]</div>
							</div>
							<div class="card mb-4">
								<div class="card-header">
									<i class="fas fa-table me-1"></i> 주문 신청 목록
								</div>

								<div class="card-body">
									<table id="datatablesSimple" class="dataTable-table">
										<thead>
											<tr>
												<th data-sortable style="width: 19.5444%;"><a>주문코드</a></th>
												<th data-sortable style="width: 19.5444%;"><a>고객사명</a></th>
												<th data-sortable style="width: 19.5444%;"><a>접수날짜</a></th>
												<th data-sortable style="width: 19.5444%;"><a>접수내용</a></th>
											</tr>
										</thead>
										<tbody>
											<tr v-for="ol in list"
												@click="mroOrderListDetail(ol.os_code)">
												<td>{{ol.os_code}}</td>
												<td>{{ol.cl_name}}</td>
												<td>{{ol.os_date}}</td>
												<td>{{ol.os_state}}</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</template>
<!------------------------------------------------------------------------------------------------------------------------>
					<template v-if="display[6].show" style="z-index:3;">
						<div v-if="modal.show"
							style="height: 100%; width: 100%; background: rgba(0, 0, 0, 0.5); position: absolute; padding: 20px; z-index: 2;">
							<div
								style="max-width: 100%; width: 83.5%; display: table; background: #fff; border-radius: 10px; padding: 20px; z-index: 1;">
								<table id="datatablesSimple" class="dataTable-table">
									<thead>
										<tr>
											<th data-sortable
												style="width: 13.3333%; background-color: #E0E0E0;"><a>공급사코드</a></th>
											<th data-sortable
												style="width: 13.3333%; background-color: #E0E0E0;"><a>공급사명</a></th>
											<th data-sortable
												style="width: 13.3333%; background-color: #E0E0E0;"><a>주문코드</a></th>
											<th data-sortable
												style="width: 13.3333%; background-color: #E0E0E0;"><a>상품코드</a></th>
											<th data-sortable
												style="width: 20%; background-color: #E0E0E0;"><a>상품명</a></th>
											<th data-sortable
												style="width: 13.3333%; background-color: #E0E0E0;"><a>상품수량</a></th>
											<th data-sortable
												style="width: 13.3333%; background-color: #E0E0E0;"><a>상태코드</a></th>
										</tr>
									</thead>
									<tbody>
										<tr v-for="old in modalDetailList">
											<td>{{old.od_prspcode}}</td>
											<td>{{old.sp_name}}</td>
											<td>{{old.od_oscode}}</td>
											<td>{{old.od_prcode}}</td>
											<td>{{old.pr_name}}</td>
											<td>{{old.od_quantity}}</td>
											<td>{{old.od_stcode}}</td>
										</tr>
										<button class="btn-datatable" v-on:click="modalClose()">
											<img src="../../resources/img/close.png">
										</button>
									</tbody>
								</table>
							</div>
						</div>
						<div class="container-fluid px-4">
							<h1 style = "padding:20px; font-size:25px; color:#808080; font-weight:bold; margin-left:-30px;"><a href="/">메인페이지</a> >교환신청목록</h1>
							
							<div class="card mb-4">
								<div class="card-body">※교환신청목록 확인 후 처리해주세요 [문의 :
									nsb214@naver.com]</div>
							</div>
							<div class="card mb-4">
								<div class="card-header">
									<i class="fas fa-table me-1"></i> 교환 신청 목록
								</div>

								<div class="card-body">
									<table id="datatablesSimple" class="dataTable-table">
										<thead>
											<tr>
												<th data-sortable style="width: 19.5444%;"><a>주문코드</a></th>
												<th data-sortable style="width: 19.5444%;"><a>고객사명</a></th>
												<th data-sortable style="width: 19.5444%;"><a>접수날짜</a></th>
												<th data-sortable style="width: 19.5444%;"><a>접수내용</a></th>
											</tr>
										</thead>
										<tbody>
											<tr v-for="ol in list"
												@click="mroOrderListDetail(ol.os_code)">
												<td>{{ol.os_code}}</td>
												<td>{{ol.cl_name}}</td>
												<td>{{ol.os_date}}</td>
												<td>{{ol.os_state}}</td>
											</tr>
											</tbody>																	
									</table>
								</div>
							</div>
						</div>
					</template>
<!------------------------------------------------------------------------------------------------------------------------>
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

        
    </body>
</html>
