$(document).ready(function(){
  $('a[href^="#"]').on('click',function (e) {
      e.preventDefault();

      var target = this.hash,
      $target = $(target);

      $('html, body').stop().animate({
          'scrollTop': $target.offset().top
      }, 900, 'swing', function () {
          window.location.hash = target;
      });
  });
});

$(document).on("scroll", function () {
    if ($(document).scrollTop() > 100) { //QUANDO O SCROLL PASSAR DOS 100px DO TOPO
        $("#navbarP").removeClass("big"); //TROCA P CLASSE MENOR
    } else {
        $("#navbarP").addClass("big");//VOLTA P MENU GRANDE ORIGINAL
    }
});
