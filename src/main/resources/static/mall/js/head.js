$('.search-list').hover(function () {
    $(this).addClass('hover');
    document.getElementById("searchCategory").style.display="block";
});
$('.all-list > .item ').hover(function () {
    $(this).addClass('hover');
    $(this).children('.list').css('display', 'block');
},function () {
    $(this).removeClass('hover');
    $(this).children('.list').css('display', 'none');
})