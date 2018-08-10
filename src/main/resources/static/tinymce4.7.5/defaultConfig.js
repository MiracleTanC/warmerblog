
const toolbar = `styleselect   fontselect   formatselect   fontsizeselect   forecolor backcolor   bold italic underline strikethrough   image  media   table  | alignleft aligncenter alignright alignjustify   outdent indent   numlist bullist   preview removeformat  hr   paste code codesample  link   undo redo   fullscreen `;

const plugins = `
	paste
    importcss
    image
    code
    codesample
    table
    advlist
    fullscreen
    link
    media
    lists
    textcolor
    colorpicker
    hr
    preview
    imagetools 
    wordcount
    `;
const content_style=`
	    *                         { padding:0; margin:0; }
	    html, body                { height:100%; }
	    img                       { max-width:100%; display:block;height:auto; }
	    a                         { text-decoration: none; }
	    iframe                    { width: 100%; }
	    p                         { line-height:1.6; margin: 0px; }
	    table                     { word-wrap:break-word; word-break:break-all; max-width:100%; border:none; border-color:#999; }
	    .mce-object-iframe        { width:100%; box-sizing:border-box; margin:0; padding:0; }
	    ul,ol                     { list-style-position:inside; }
	  `;
const fontsize_formats='10px 11px 12px 14px 16px 18px 20px 24px';
const style_formats=[
    {
        title: '首行缩进',
        block: 'p',
        styles: { 'text-indent': '2em' }
      },
      {
        title: '行高',
        items: [
          {title: '1', styles: { 'line-height': '1' }, inline: 'span'},
          {title: '1.5', styles: { 'line-height': '1.5' }, inline: 'span'},
          {title: '2', styles: { 'line-height': '2' }, inline: 'span'},
          {title: '2.5', styles: { 'line-height': '2.5' }, inline: 'span'},
          {title: '3', styles: { 'line-height': '3' }, inline: 'span'}
        ]
      }
    ];
const font_formats=`
		    微软雅黑=微软雅黑;
		    宋体=宋体;
		    黑体=黑体;
		    仿宋=仿宋;
		    楷体=楷体;
		    隶书=隶书;
		    幼圆=幼圆;
		Andale Mono=andale mono,times;
		Arial=arial, helvetica,
		sans-serif;
		Arial Black=arial black, avant garde;
		Book Antiqua=book antiqua,palatino;
		Comic Sans MS=comic sans ms,sans-serif;
		Courier New=courier new,courier;
		Georgia=georgia,palatino;
		Helvetica=helvetica;
		Impact=impact,chicago;
		Symbol=symbol;
		Tahoma=tahoma,arial,helvetica,sans-serif;
		Terminal=terminal,monaco;
		Times New Roman=times new roman,times;
		Trebuchet MS=trebuchet ms,geneva;
		Verdana=verdana,geneva;
		Webdings=webdings;
		Wingdings=wingdings,zapf dingbats`;