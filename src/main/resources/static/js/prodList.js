/**
 * 
 */
 $(function(){
	$.get("/common/products",function(listHtmlData){
		$("#prod-list>.wrap").html(listHtmlData);
		
	});
});