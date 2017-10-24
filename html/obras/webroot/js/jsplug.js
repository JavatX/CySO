$(window).scroll(function() {
  if( $(this).scrollTop() > $('navegadorobra').height() ) {
    $(".navegadorobra").removeClass("navegadorobraD").addClass("navegadorobraDS");
  } else {
    $(".navegadorobra").removeClass("navegadorobraDS").addClass("navegadorobraD");
  }
});


$(document).ready(function() {
  $("[data-toggle]").click(function() {
    var toggle_el = $(this).data("toggle");
    $(toggle_el).toggleClass("open-sidebar");
  });
     
});
 
$(".swipe-area").swipe({
    swipeStatus:function(event, phase, direction, distance, duration, fingers)
        {
            var width = $(window).width();
            if(width > 767){
                if (phase=="move" && direction =="left") {
                     $(".container").addClass("open-sidebar");
                     return false;
                }
                if (phase=="move" && direction =="right") {
                     $(".container").removeClass("open-sidebar");
                     return false;
                }
            }else{
                if (phase=="move" && direction =="bottom") {
                     $(".navegadorobraD").addClass("open-sidebar");
                     return false;
                }
                if (phase=="move" && direction =="top") {
                     $(".navegadorobraD").removeClass("open-sidebar");
                     return false;
                }
            }
        }
});

$(".swipe-area").swipe2({
    swipeStatus:function(event, phase, direction, distance, duration, fingers)
        {
            var width = $(window).width();
            if(width > 767){
                if (phase=="move" && direction =="left") {
                     $(".containerobra").addClass("open-sidebar");
                     $(".interior").css({
                                            'overflow-x': ''
                                        });
                     $(".hinterior").css({
                                            'overflow-x': ''
                                        });
                     return false;
                }
                if (phase=="move" && direction =="right") {
                     $(".containerobra").removeClass("open-sidebar");
                     $(".interior").css({
                                            'overflow-x': 'hidden'
                                        });
                     $(".hinterior").css({
                                            'overflow-x': 'hidden'
                                        });
                     return false;
                }
            }else{
                if (phase=="move" && direction =="bottom") {
                     $(".containerobra").addClass("open-sidebar");
                     return false;
                }
                if (phase=="move" && direction =="top") {
                     $(".containerobra").removeClass("open-sidebar");
                     return false;
                }
            }
        }
});

$(".swipe-area").swipe3({
    swipeStatus:function(event, phase, direction, distance, duration, fingers)
        {
            var width = $(window).width();
            if(width > 767){
                if (phase=="move" && direction =="left") {
                     $(".containerobraD").addClass("open-sidebar");
                     $(".interior").css({
                                            'overflow-x': ''
                                        });
                     $(".hinterior").css({
                                            'overflow-x': ''
                                        });
                     return false;
                }
                if (phase=="move" && direction =="right") {
                     $(".containerobraD").removeClass("open-sidebar");
                     $(".interior").css({
                                            'overflow-x': 'hidden'
                                        });
                     $(".hinterior").css({
                                            'overflow-x': 'hidden'
                                        });
                     return false;
                }
            }else{
                if (phase=="move" && direction =="bottom") {
                     $(".containerobraD").addClass("open-sidebar");
                     return false;
                }
                if (phase=="move" && direction =="top") {
                     $(".containerobraD").removeClass("open-sidebar");
                     return false;
                }
            }
        }
});

$( ".sidebar-toggle" ).click(function() {
    //alert($( this ).css( "transform" ));
    if (  $( this ).css( "transform" ) == 'none' ){
        $(this).css("transform","rotate(90deg)");
    } else {
        $(this).css("transform","" );
    }
});
