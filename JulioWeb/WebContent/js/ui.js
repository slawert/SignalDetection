$(document).ready(function() {
	$('.experience').mouseenter(function(){
		clearDivs($('#experience'));
		show_elements($('#experience > *'),'#007A9B');
		show_content( $('#experience'));
	});

	
	$('.academic').mouseenter(function(){
		clearDivs($('#academic'));
		show_elements($('#academic > *'), '#FF8F0C');
		show_content( $('#academic'));
	});

	
	$('.technical').mouseenter(function(){
		clearDivs($('#technical'));
		show_elements($('#technical > *'), '#6B006B');
		show_content( $('#technical'));
	});

	
	$('.skills').mouseenter(function(){
		clearDivs($('#skills'));
		show_elements($('#skills > *'), '#B82500');
		show_content( $('#skills'));
	});

	$('.contact').mouseenter(function(){
		clearDivs($('#contact'));
		show_elements($('#contact > *'), '#189337');
		show_content( $('#contact'));
	});
	
});

var clearDivs = function($div){
	var $allContentDivs = $('#content').find('div');
	for(var i = 0 ; i < $allContentDivs.length ; i++){
		if($div.attr('id') != $allContentDivs.eq(i).attr('id')){
			hide_content($allContentDivs.eq(i),2);
			hide_elements($allContentDivs.eq(i).find('*'));
			//hide_content($('#contact'),5);
			//hide_elements($('#contact > *'));
		}
	}
};


var show_content= function($div){
	$div.css('opacity','1');
	$div.css('z-index',6);
	$div.css('background','#FFF8EF');
	$div.css('margin-top', '3%');
	$div.css('overflow', 'auto');
};

var hide_content = function($div,index){
	$div.css('opacity','0.15');
	$div.css('z-index',index);
	$div.css('background','none');
	$div.css('margin-top', '0%');
	$div.css('overflow', 'none');
};

var show_elements= function($elements, color){
	$elements.css('color', color);
	$elements.css('font-weight', 'normal');
	$elements.css('font-size', '0.4em');
};


var hide_elements= function($elements){
	$elements.css('color', '#666666');
	$elements.css('font-size', '1em');
	$elements.css('font-weight', 'bolder');
};




