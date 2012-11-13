<g:if test="${!veroApiKey}">
    <script type="text/javascript">
        // Kissmetrics
        var _kmq = _kmq || [];
        var _kmk = _kmk || '${apiKey}';
        function _kms(u){
            setTimeout(function(){
                var d = document, f = d.getElementsByTagName('script')[0],
                        s = d.createElement('script');
                s.type = 'text/javascript'; s.async = true; s.src = u;
                f.parentNode.insertBefore(s, f);
            }, 1);
        }
        _kms('//i.kissmetrics.com/i.js');
        _kms('//doug1izaerwt3.cloudfront.net/' + _kmk + '.1.js');
    </script>
</g:if>
<g:else>
    <script type="text/javascript">
        // Kissmetrics + Vero init
        var _kmq = _kmq || [];
        function _kms(u){setTimeout(function(){var s=document.createElement('script');var f=document.getElementsByTagName('script')[0];s.type='text/javascript';s.async=true;s.src=u;f.parentNode.insertBefore(s,f);},1);}
        loadKissmetrics = function () {
            var _kmk = _kmk || '${apiKey}';
            _kms('//i.kissmetrics.com/i.js');_kms('//doug1izaerwt3.cloudfront.net/' + _kmk + '.1.js');
        };

        // Vero
        var _veroq = _veroq || [];
        setTimeout(function(){if(typeof window.Semblance=="undefined"){console.log("Vero did not load in time.");for(var i=0;i<_veroq.length;i++){a=_veroq[i];if(a.length==3&&typeof a[2]=="function")a[2](null,false);}}},3000);
        _veroq.push(['init', {
            api_key: '${veroApiKey}'
        }, function(vero, loaded) {
            if (loaded)
                window._kmq.splice(0, 0, vero.listeners.attach_to_kissmetrics);
            loadKissmetrics();
        }]);
        (function() {var ve = document.createElement('script'); ve.type = 'text/javascript'; ve.async = true; ve.src = '//www.getvero.com/assets/m.js'; var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ve, s);})();
    </script>
</g:else>