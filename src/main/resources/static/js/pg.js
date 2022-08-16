/**
 * 
 */
$(function(){
	$("#btn-payment").click(payment);
});

function paymentBtnClicked(){
	var orderItems=$(".eaPrice");
	var eaPriceTot=0;
	
	for(var i=0; i<orderItems.length; i++){
		eaPriceTot+=parseInt($(orderItems[i]).find(".eaPrice").text());
		name=$(orderItems[0]).find(".name").text();
	}
	var prodName=$(orderItems[0]).find(".name").text();
	if(orderItems.length>1){
		prodName+="외" + (orderItems.length-1)+"건"
	}
	var email=$("#userEmail").text();
	var userName=$("#userName").text();
	var phone=$("#phone").val();
	var buyer_addr=$("#roadAddress").val()+" "+$("#jibunAddress").val()+" "+$("#detailAddress").val()+" "+$("#extraAddress").val();
	var buyer_postcode=$("#postcode").val();
	
	payment(prodName,eaPriceTot,email,userName,phone,buyer_addr,buyer_postcode)

}

function payment(prodName,eaPriceTot,email,userName,phone,buyer_addr,buyer_postcode){
	var r=confirm("결제 하시겠습니까?");
	if(!r){
		return;
	}
	var IMP = window.IMP; // 생략 가능
    IMP.init("imp25580451"); // 예: imp00000000
    
    //결제금액
    //주문자
    
    IMP.request_pay({ // param
          pg: "kakaopay",
          pay_method: "card",
          merchant_uid: "ORD"+new Date().getTime(),
          name: prodName,
          amount: eaPriceTot,
          buyer_email: email,
          buyer_name: userName,
          buyer_tel: phone,
          buyer_addr:buyer_addr,
          buyer_postcode: buyer_postcode
      }, function (rsp) { // callback
     	  msg="";
          if (rsp.success) {
              // 결제 성공 시 로직,
              console.log(rsp);
              msg="결제가 완료 되었습니다.";
              //db에 정보 저장
              $("#form-order").submit();
          } else {
              // 결제 실패 시 로직,
              msg="결제에 실패하였습니다.";
          }
          alert(msg);
      });
}
