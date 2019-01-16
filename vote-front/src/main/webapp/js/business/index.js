var url = "";
var currentPage = 1;
var onePageSize = 10;

window.onload=function(){
	list();
};

function list(){
	url = contextPath+"/allList";
	$.ajax({
		   async:false,
		   data:{currentPage:currentPage,onePageSize:onePageSize},
		   dataType:"json",
		   url:url,
		   type:"POST",
		   success:function(data) {
			   if(data.errorType == 'SUCCESS'){
				   var votes = data.objects;
				   var votelist = votesHtml(votes);
				   if(currentPage == 1){
					   $("#skills").html(votelist);
				   }else{
					   $("#skills").append(votelist);
				   }
				   currentPage++;
			   }else {
				   alert('服务器繁忙...');
			   }
           }, 
           error:function(){
              console.log("服务器繁忙...");
           }
   })
}

function sexrank(sex){
	url = contextPath+"/sexrank";
	$.ajax({
		   async:false,
		   data:{currentPage:currentPage,onePageSize:onePageSize,sex:sex},
		   dataType:"json",
		   url:url,
		   type:"POST",
		   success:function(data) {
			   if(data.errorType == 'SUCCESS'){
				   var votes = data.objects;
				   var votelist = votesHtml(votes);
				   if(currentPage == 1){
					   $("#skills").html(votelist);
				   }else{
					   $("#skills").append(votelist);
				   }
				   currentPage++;
			   }else {
				   alert('服务器繁忙...');
			   }
        }, 
        error:function(){
           console.log("服务器繁忙...");
        }
})
}

$(".filter").click(function(){
	currentPage = 1;
	$("#skills").html('');
	var filter = $(this).attr('data-filter');
	if(filter == 'all'){
		list();
	}else if(filter == 'photography'){
		sexrank('male');
	}else if(filter == 'design'){
		sexrank('female');
	}
})

function votesHtml(votes){
	   if(votes == null || votes.length == 0)
		   return;
	   var votelist = "";
	   for(var i = 0;i < votes.length ; i++){
		   var vote = votes[i];
		   var vote_id = vote.id;
		   var vote_title = vote.title;
		   var vote_options = vote.voteOptionMini;
		   var options = "";
		   for(var j = 0 ; j < vote_options.length ; j++){
			   var option = vote_options[j];
			   options += vote_option_html.replace(rplc("option_id"),option.id)
	                                      .replace(rplc("option_desc"),option.option);
		   }
		   votelist += vote_html.replace(rplc("vote_id"),vote_id)
		                        .replace(rplc("vote_title"),vote_title)    
		                        .replace(rplc("vote_options"),options);         
	   }
	   return votelist;
}

var vote_html = "<div class=\"container\" id=\"portfolio-items-wrapper\" dataVal=\"vote_id\" onclick=\"window.location.href='/detail?id=vote_id'\">"+
                      "<div class=\"row\">"+
                          "<div class=\"col-md-5\">"+
                             "<div class=\"team-skills-content\">"+
                                "<h4 style=\"font-weight: 800;\">vote_title</h4>"+
                                "<!-- <p>vote.title<br></p> -->"+
                             "</div>"+
                          "</div>"+
                          "<div class=\"col-md-6 col-md-offset-1\">"+
                              "<div class=\"progress-block\" style=\"margin-top: 20px;\">"+
                                  "<ul>"+
                                      "vote_options"+
                                  "</ul>"+
                              "</div>"+
                          "</div>"+
                       "</div>  		<!-- End row -->"+
                     "</div>   	<!-- End container -->";

var vote_option_html = "<li dataVal=\"option_id\">"+
                                 "<span>option_desc</span>"+
                                 "<div class=\"progress\">"+
                                     "<div class=\"progress-bar\" style=\"width: 90%;\"></div>"+
                                 "</div>"+
                       "</li>";

function rplc(str){
	return new RegExp(str,"gm");
}