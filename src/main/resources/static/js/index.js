/**
 * 인덱스페이지의 js적용
 */
//ready//////////////////////////
/*브라우저가 로딩되면 자동으로 실행*/

$(function(){
	//console.log(timeData);
	/*ajax를 이용해서 서버에 접속해서 DB데이터를 JSON갖고옵니다.*/
	//*

	//console.log(gList.list);
	$.ajax({
		url: "goods/time",//요청,
		success:function(result){
			$("#time>.wrap").html(result);
			//html()실행하면 time-list 태그가 삽입됩니다.
			// .btn-wrap .next 태그가 존재하는 영역에서 등록이 되야합니다.
		}
	});
	
	$.ajax({
		url: "goods/glist",
		type:"post",
		//요청-->응답이 성공적으로 실행될때 이후 작업할수 있는함수
		success: function(result){
			$(".disp").html(result);
		}
	});
	
	/*$.get("goods/glist",function(){});*/
	
	//*/
});
////////////////////////////