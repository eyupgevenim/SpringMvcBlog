<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Home</title>
</head>
<body>
 
	<section id="home" class="text-center">
         
                <div id="carousel-example" class="carousel slide" data-ride="carousel">

                    <div class="carousel-inner">
                        <div class="item active">

                            <img src="<c:url value="/resources/img/home/slider-desk.jpg" />" style="height: 600px;width: 100%" alt="" />
                            <div class="carousel-caption" >
                                <h4 class="back-light">Tutkularınızı kendi tarzınızla paylaşın Benzersiz, göz alıcı bir blog hazırlayın.</h4>
                                <p > Hizmetimiz ücretsizdir ve kullanımı kolaydır.</p>
                            </div>
                        </div>
                        <div class="item">
                            <img src="<c:url value="/resources/img/home/slider-ckfinder.png" />" style="height: 600px" alt="" />
                            <div class="carousel-caption ">
                                <h4 class="back-light">Mükemmel tasarımı seçin</h4>
                            </div>
                        </div>
                        <div class="item">
                            <img src="<c:url value="/resources/img/home/slider-ac.png" />" style="height: 600px" alt="" />
                            <div class="carousel-caption ">
                                <h4 class="back-light">Milyonlarca kullanıcı arasında siz de yerinizi alın.</h4>
                            </div>
                        </div>
                    </div>

                    <ol class="carousel-indicators">
                        <li data-target="#carousel-example" data-slide-to="0" class="active"></li>
                        <li data-target="#carousel-example" data-slide-to="1"></li>
                        <li data-target="#carousel-example" data-slide-to="2"></li>
                    </ol>
                    
                    <!-- Controls -->
				  <a class="left carousel-control" href="#carousel-example" role="button" data-slide="prev">
				    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				    <span class="sr-only">Previous</span>
				  </a>
				  <a class="right carousel-control" href="#carousel-example" role="button" data-slide="next">
				    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				    <span class="sr-only">Next</span>
				  </a>
                    
                </div>
           
       </section>
    <!--/.SLIDESHOW END-->
    
    
    <!-- 1.. -->
    <div class="container-fluid mt0 pt0">
		<div class="row mt0 pt0" style="background-image:url(/blog.com/resources/img/home/promotion.jpg); height: 630px;">
			<div class="col-md-offset-8 col-md-4">
				<h1>Mükemmel tasarımı seçin</h1>
				<p style="font-size:14pt"> Kendi tarzınıza uygun, göz alıcı bir blog hazırlayın. 
				Uyarlanabilen düzenlere sahip, yüzlerce arka plan resmi
				 arasından seçim yapma imkanı sunan pratik
				  şablonlarımızdan birini seçin. Kendi tasarımınızı sıfırdan
				   oluşturmayı da tercih edebilirsiniz. </p>
			</div>
		</div>
	</div>
	
	<!-- 2.. -->
	<div class="container-fluid mt0 pt0">
		<div class="row mt0 pt0" 
		style="background-color:rgb(56, 141, 128);
				height: 630px;">
			<div class="col-md-offset-4 col-md-4" style="margin-top:250px">
				<h1> Ücretsiz bir alan edinin</h1>
				<p style="font-size:14pt">Blogunuza mükemmel bir yuva sunun. 
				Ücretsiz bir blogspot.com alanı edinin veya birkaç 
				tıklamayla özel bir alan satın alın. </p>
			</div>
		</div>
	</div>
	
	<!-- 3.. -->
	<div class="container-fluid mt0 pt0">
		<div class="row mt0 pt0" 
		style="background-image:url(/blog.com/resources/img/home/appls-for-ed.jpg);
				background-repeat: no-repeat;
			    background-size: 100% auto;
			    height: 630px;
			    margin-bottom:-22px">
			<div class="col-md-4">
				<h1> Anılarınız hep sizinle olsun</h1>
				<p style="font-size:14pt"> Değerli anılarınızı kaybetmeyin. 
				Blogger'la binlerce yayını, fotoğrafı ve daha pek çok şeyi 
				Blog'ta ücretsiz olarak, gözünüz arkada kalmadan saklama imkanına sahip olursunuz. </p>
			</div>
		</div>
	</div>
	
	
	<!-- 4.. -->
	<div class="container-fluid mt0 pt0">
		<div class="row mt0 pt0" 
		style="background-color:rgb(255, 128, 0);;
				height: 630px;">
			<div class="col-md-offset-4 col-md-4" style="margin-top:230px">
				<h1> Para kazanın</h1>
				<p style="font-size:14pt">Özenli çalışmalarınızın meyvesini toplayın. 
				Google AdSense, alakalı şekilde hedeflenmiş reklamları 
				blogunuzda otomatik olarak gösterebilir. Böylece siz de, 
				tutkunuzu diğer insanlarla paylaşırken para kazanabilirsiniz. </p>
			</div>
		</div>
	</div>
	
	<!-- 5.. -->
	<div class="container-fluid mt0 pt0">
	
		<div class="col-md-offset-3 col-md-6" style="margin-top:100px">
				<h1>Milyonlarca kullanıcı arasında siz de yerinizi alın </h1>
				<p style="font-size:14pt"><b> İster en son haberleri, ister deneyimlerinizi
				 veya aklınızdakileri paylaşıyor olun, Blogger'da asla yalnızlık çekmezsiniz. 
				 Kaydolun ve tutkularını paylaşmak için neden milyonlarca 
				 kişinin bizi tercih ettiğini siz de keşfedin. </b></p>
			</div>
	
		<div class="row mt0 pt0" 
		style="background-image:url(/blog.com/resources/img/home/hires.jpg);
				background-repeat: no-repeat;
				background-size: 100% auto;
				height: 630px;
				opacity: 0.1;
				margin-bottom:-160px">
			
		</div>
	</div>

</body>
</html>
