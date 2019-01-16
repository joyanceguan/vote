var url = contextPath+"/typeList";
var currentPage = 1;
var onePageSize = 10;

window.onload=function(){
	typelist(page);
};

function typelist(){
	var typeId = -1; 
	if(page == 'technology'){
		typeId = 4;
	}else if(page == 'ent'){
		typeId = 9;
	}else if(page == 'live'){
		typeId = 8;
	}else if(page == 'international'){
		typeId = 10;
	}else if(page == 'sports'){
		typeId = 1;
	}
	
	$.ajax({
		   async:false,
		   data:{currentPage:currentPage,onePageSize:onePageSize,typeId:typeId},
		   dataType:"json",
		   url:url,
		   type:"POST",
		   success:function(data) {
			   if(data.errorType == 'SUCCESS'){
				   if(currentPage == 1){
					   var sortVotes = data.extendInfo;
					   //排行展示
				   }
				   var votes = data.objects;
				   votesHtml(votes);
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

function votesHtml(votes){
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
	   $("#skills").append(votelist);
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