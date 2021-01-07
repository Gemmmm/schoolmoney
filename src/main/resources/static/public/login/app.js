$(function(){
	$(".box .slideNav dt").on("click",function(){
		var sib = $(this).parent().siblings();
		$(this).parent().toggleClass("current");
		sib.removeClass("current");
	
	});
});
 //图片预览
function upload(f){
    if(f.files){
        for(var i=0;i<f.files.length;i++){
            var reader = new FileReader();
            reader.readAsDataURL(f.files[i]);
        }

        reader.onload = function(e){
            $(f).next('img').attr('src',e.target.result);
            $(f).next('img').attr('data-src',e.target.result.substring(0,4));
        }
    }
}
// 单张图上传
$("document").ready(function () {
    $('.upImg0').delegate('.img','click',function(){
        div=$(this).parent();
        file=$(this).parent().find('.hidden-file');
        file.click();
        file_data=file.attr('data-file');
    });
    //图片上传判定
    $(".upImg0").on('change','.hidden-file',function (e) {
    	upload(this);
        slf = $(this).parent('.upImg0');
        slf_f=slf.clone(true);
        src=$(this).next('img').attr('src');
        var path = $(this).val();
        extStart = path.lastIndexOf('.');
        ext = path.substring(extStart, path.length).toUpperCase();//获取上传图片的后缀名
        var size = this.files[0].size / 1024;//图片尺寸
        //没有图片选择
        if (ext == '') {
            return;
        }
        //判断图片格式
        if (ext !== '.PNG' && ext !== '.JPG' && ext !== '.JPEG' && ext !== '.GIF') {
            $('.commtips1').addClass('pink');
            slf.after(slf_f);
            slf.remove();
            return false;
        }else{
        	 $('.commtips1').removeClass('pink');
        }
        //图片尺寸的判断
       	if (size > 500) {
            $('.commtips1').addClass('pink');
            slf.after(slf_f);
            slf.remove();
            return false;

        }else{
        	$('.commtips1').removeClass('pink');
        }
    });
});
// 多图上传
$("document").ready(function () {
    $('.slf_ImgBox').delegate('.upImg1 .img', 'click', function () {
        file=$(this).prev('input');
        file.click();
    });

    $('.slf_ImgBox').delegate('.upImg1 input', 'change', function () {
        div = $(this).parent();
        parents = $(this).parents('.slf_ImgBox');
        len = parents.children('.upImg1').length;
        span = $('<span style="padding:0 5px;text-indent:-9999em;float:left">1</span>');
        file_data = $(this).attr('data-file');

        upload(this);//图片预览
        $('.commtips2').removeClass('pink');
        if (len < 5 && (file_data == 1)){
            $(this).attr('data-file',2);
            div.after(span);
            spans = div.next('span');
            f=div.clone(true);
            f.find('input').val('');
            f.find('input').attr('data-file',1);
            spans.after(f);

        }
    });
});
//图片上传判定
$(".upImg1").on('change', '.hidden-file', function (e) {
    slf = $(this).parent('.upImg1');
    slf_f = slf.clone(true);
    src = $(this).next('img').attr('src');
    var path = $(this).val();
    extStart = path.lastIndexOf('.');
    ext = path.substring(extStart, path.length).toUpperCase();

    //没有图片选择
    if (ext == '') {
        return;
    }
    //判断图片格式
    if (ext !== '.PNG' && ext !== '.JPG' && ext !== '.JPEG' && ext !== '.GIF') {
        $('.commtips2').addClass('pink');
        if (src == 'images/upImg.jpg') {
            console.log(1);
            slf.after(slf_f);
            slf.remove();
        }
        if (src != 'images/upImg.jpg') {
            console.log(2);
            slf.after(slf_f);
            slf.remove();
        }
        return false;
    }

    //获取图片大小，注意使用this，而不是$(this)
    var size = this.files[0].size / 1024;
    if (size > 800) {
        $('.commtips2').addClass('pink');
        if (src == 'images/upImg.jpg') {
            slf.after(slf_f);
            slf.remove();
        }
        if (src != 'images/upImg.jpg') {
            slf.after(slf_f);
            slf.remove();
        }
        return false;
    }
});
/***菜单样式照应***/
function menu_css() {
    whref=window.location.pathname;
    arr_w=whref.split('/');
    ahtml=arr_w.pop();
    //分页p不存在时
    if(typeof arr_w[4] =='undefined')
    {
        arr_w[4]='undefined';
    }
    //分页p存在时
    if(arr_w[4].toLowerCase()=='p')
    {
        ahtml=arr_w[3];
    }
    $('.slideNav dl dt a').each(function () {
        ahref=$(this).attr('href');
        arr_a=ahref.split('/');
        html=arr_a.pop();
        arr_h=html.split('.');
        if(ahtml.toLowerCase().indexOf(arr_h[0].toLowerCase())>-1)
        {
            $(this).parents('dl').addClass('current');
        }
    });
}
menu_css();
//商家订单状态操作
function order_status(el,ordersn,flag) {
    a_tag=$(el);
    if(ordersn)
    {
        $.ajax({
            url:'/Home/Index/order_status',
            type:'post',
            data:{ordersn:ordersn,flag:flag},
            dataType:'json',
            success:function (result) {
                if(result.status==200)
                {
                    switch(flag)
                    {
                        case 1:
                            a_tag.html('待收货');
                            a_tag.removeClass('sureOrder');
                            a_tag.removeAttr('onclick');
                            break;
                        case 71:
                            a_tag.html('退款中');
                            a_tag.removeClass('sureOrder');
                            a_tag.removeAttr('onclick');
                            a_tag.next('a').remove();
                            break;
                        case 77:
                            a_tag.html('申请退款被拒');
                            a_tag.removeClass('sureOrder');
                            a_tag.removeAttr('onclick');
                            a_tag.prev('a').remove();
                            break;
                    }
                }
                if(result.status==400)
                {
                    alert(result.msg);
                }
            },
            error:function () {
                console.log('order_status ajax error');
            }
        })
    }
}