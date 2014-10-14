/**
 * Setting for data table
 */
$(function() {
    $('#searchResult').dataTable({
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        "bSort": true,
        "bInfo": true,
        "bAutoWidth": false,
        oLanguage: {
            // sUrl: 'http://cdn.datatables.net/plug-ins/a5734b29083/i18n/Vietnamese.json'
        	sUrl: 'resources/AdminLTE/js/plugins/datatables/Vietnamese.json'
        }
    });

});
