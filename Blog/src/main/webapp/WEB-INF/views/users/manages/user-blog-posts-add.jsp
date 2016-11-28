<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Blog1 - Yazı Ekle</title>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

	<!-- 
	<script type="text/javascript">
		$(document).ready( function() {
			$("#txtEditor").Editor();   
		});
	
	</script>
	 -->

	<!-- Main Content -->
	<div class="container-fluid">
		<div class="side-body">
			<h1>Blog1 - Yazı Ekle</h1>
			<hr>
			<form action="#" class="form-group">
				<div class="row">
					<h4>Başlık</h4>
				</div>
				<div class="row">
					<div class="input-group col-xs-12 col-md-6">
						<input type="text" id="post-title" class=" form-control" name="name" value="">
					</div>
				</div>
				<br>
				<div class="row">
					 <div id="summernote"> </div>
				</div>
				<br>
				<div class="row">
					<button class="col-xs-6 pull-right col-md-1 btn btn-success" style="margin-left:10px">Yayınla</button>
					<button class="col-xs-6 pull-right col-md-1 btn btn-warning" style="margin-left:10px">Taslak</button>
					<button class="col-xs-6 pull-right col-md-2 btn btn-default" style="margin-left:10px"
					 	type="button" id="btn-preview" data-toggle="modal" data-target=".bs-example-modal-lg">
					 	<span class="glyphicon glyphicon-duplicate" aria-hidden="true"></span>Önizleme
					 </button>
				</div>
			</form>
		</div>
	</div>
	
	
	
<!-- Model -->
<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalTitle">Modal title</h4>
      </div>
      <div class="modal-body">
        <div id="preview">
        
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Kapat</button>
      </div>
    </div>
  </div>
</div>
	
	

</body>

</html>
