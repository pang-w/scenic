(function() {
	var h = {},
	mt = {},
	c = {
		id: "6338835ad35c6d950a554fdedb598e48",
		dm: ["ghostchina.com"],
		js: "tongji.baidu.com/hm-web/js/",
		etrk: [],
		icon: '',
		ctrk: true,
		align: 1,
		nv: -1,
		vdur: 1800000,
		age: 31536000000,
		rec: 0,
		rp: [],
		trust: 0,
		vcard: 0,
		qiao: 0,
		lxb: 0,
		conv: 0,
		med: 0,
		cvcc: '',
		cvcf: [],
		apps: ''
	};
	var r = void 0,
	s = !0,
	t = null,
	u = !1;
	mt.cookie = {};
	mt.cookie.set = function(a, b, d) {
		var g;
		d.K && (g = new Date, g.setTime(g.getTime() + d.K));
		document.cookie = a + "=" + b + (d.domain ? "; domain=" + d.domain: "") + (d.path ? "; path=" + d.path: "") + (g ? "; expires=" + g.toGMTString() : "") + (d.qb ? "; secure": "")
	};
	mt.cookie.get = function(a) {
		return (a = RegExp("(^| )" + a + "=([^;]*)(;|$)").exec(document.cookie)) ? a[2] : t
	};
	mt.f = {};
	mt.f.wa = function(a) {
		return document.getElementById(a)
	};
	mt.f.M = function(a, b) {
		var d = [],
		g = [];
		if (!a) return g;
		for (; a.parentNode != t;) {
			for (var f = 0,
			p = 0,
			n = a.parentNode.childNodes.length,
			e = 0; e < n; e++) {
				var l = a.parentNode.childNodes[e];
				if (l.nodeName === a.nodeName && (f++, l === a && (p = f), 0 < p && 1 < f)) break
			}
			if ((n = "" !== a.id) && b) {
				d.unshift("#" + encodeURIComponent(a.id));
				break
			} else n && (n = "#" + encodeURIComponent(a.id), n = 0 < d.length ? n + ">" + d.join(">") : n, g.push(n)),
			d.unshift(encodeURIComponent(String(a.nodeName).toLowerCase()) + (1 < f ? "[" + p + "]": ""));
			a = a.parentNode
		}
		g.push(d.join(">"));
		return g
	};
	mt.f.Fa = function(a) {
		return (a = mt.f.M(a, s)) && a.length ? String(a[0]) : ""
	};
	mt.f.jb = function(a) {
		return mt.f.M(a, u)
	};
	mt.f.xa = function(a) {
		var b;
		for (b = "A"; (a = a.parentNode) && 1 == a.nodeType;) if (a.tagName == b) return a;
		return t
	};
	mt.f.za = function(a) {
		return 9 === a.nodeType ? a: a.ownerDocument || a.document
	};
	mt.f.Da = function(a) {
		var b = mt.f.za(a),
		d = {
			top: 0,
			left: 0
		};
		if (a) return b = b.documentElement,
		"undefined" !== typeof a.getBoundingClientRect && (d = a.getBoundingClientRect()),
		{
			top: d.top + (window.pageYOffset || b.scrollTop) - (b.clientTop || 0),
			left: d.left + (window.pageXOffset || b.scrollLeft) - (b.clientLeft || 0)
		}
	}; (mt.f.ba = function() {
		function a() {
			if (!a.D) {
				a.D = s;
				for (var b = 0,
				d = g.length; b < d; b++) g[b]()
			}
		}
		function b() {
			try {
				document.documentElement.doScroll("left")
			} catch(d) {
				setTimeout(b, 1);
				return
			}
			a()
		}
		var d = u,
		g = [],
		f;
		document.addEventListener ? f = function() {
			document.removeEventListener("DOMContentLoaded", f, u);
			a()
		}: document.attachEvent && (f = function() {
			"complete" === document.readyState && (document.detachEvent("onreadystatechange", f), a())
		}); (function() {
			if (!d) if (d = s, "complete" === document.readyState) a.D = s;
			else if (document.addEventListener) document.addEventListener("DOMContentLoaded", f, u),
			window.addEventListener("load", a, u);
			else if (document.attachEvent) {
				document.attachEvent("onreadystatechange", f);
				window.attachEvent("onload", a);
				var g = u;
				try {
					g = window.frameElement == t
				} catch(n) {}
				document.documentElement.doScroll && g && b()
			}
		})();
		return function(b) {
			a.D ? b() : g.push(b)
		}
	} ()).D = u;
	mt.event = {};
	mt.event.c = function(a, b, d) {
		a.attachEvent ? a.attachEvent("on" + b,
		function(b) {
			d.call(a, b)
		}) : a.addEventListener && a.addEventListener(b, d, u)
	};
	mt.event.preventDefault = function(a) {
		a.preventDefault ? a.preventDefault() : a.returnValue = u
	}; (function() {
		var a = mt.event;
		mt.j = {};
		mt.j.Z = /msie (\d+\.\d+)/i.test(navigator.userAgent);
		mt.j.Pa = /msie (\d+\.\d+)/i.test(navigator.userAgent) ? document.documentMode || +RegExp.$1: r;
		mt.j.cookieEnabled = navigator.cookieEnabled;
		mt.j.javaEnabled = navigator.javaEnabled();
		mt.j.language = navigator.language || navigator.browserLanguage || navigator.systemLanguage || navigator.userLanguage || "";
		mt.j.Wa = (window.screen.width || 0) + "x" + (window.screen.height || 0);
		mt.j.colorDepth = window.screen.colorDepth || 0;
		mt.j.orientation = 0; (function() {
			function b() {
				var a = 0;
				window.orientation !== r && (a = window.orientation);
				screen && (screen.orientation && screen.orientation.angle !== r) && (a = screen.orientation.angle);
				mt.j.orientation = a
			}
			b();
			a.c(window, "orientationchange", b)
		})();
		return mt.j
	})();
	mt.l = {};
	mt.l.parse = function() {
		return (new Function('return (" + source + ")'))()
	};
	mt.l.stringify = function() {
		function a(a) { / ["\\\x00-\x1f]/.test(a)&&(a=a.replace(/["\\\x00 - \x1f] /g,
			function(a) {
				var b = d[a];
				if (b) return b;
				b = a.charCodeAt();
				return "\\u00" + Math.floor(b / 16).toString(16) + (b % 16).toString(16)
			}));
			return '"' + a + '"'
		}
		function b(a) {
			return 10 > a ? "0" + a: a
		}
		var d = {
			"\b": "\\b",
			"\t": "\\t",
			"\n": "\\n",
			"\f": "\\f",
			"\r": "\\r",
			'"': '\\"',
			"\\": "\\\\"
		};
		return function(d) {
			switch (typeof d) {
			case "undefined":
				return "undefined";
			case "number":
				return isFinite(d) ? String(d) : "null";
			case "string":
				return a(d);
			case "boolean":
				return String(d);
			default:
				if (d === t) return "null";
				if (d instanceof Array) {
					var f = ["["],
					p = d.length,
					n,
					e,
					l;
					for (e = 0; e < p; e++) switch (l = d[e], typeof l) {
					case "undefined":
					case "function":
					case "unknown":
						break;
					default:
						n && f.push(","),
						f.push(mt.l.stringify(l)),
						n = 1
					}
					f.push("]");
					return f.join("")
				}
				if (d instanceof Date) return '"' + d.getFullYear() + "-" + b(d.getMonth() + 1) + "-" + b(d.getDate()) + "T" + b(d.getHours()) + ":" + b(d.getMinutes()) + ":" + b(d.getSeconds()) + '"';
				n = ["{"];
				e = mt.l.stringify;
				for (p in d) if (Object.prototype.hasOwnProperty.call(d, p)) switch (l = d[p], typeof l) {
				case "undefined":
				case "unknown":
				case "function":
					break;
				default:
					f && n.push(","),
					f = 1,
					n.push(e(p) + ":" + e(l))
				}
				n.push("}");
				return n.join("")
			}
		}
	} ();
	mt.lang = {};
	mt.lang.d = function(a, b) {
		return "[object " + b + "]" === {}.toString.call(a)
	};
	mt.lang.nb = function(a) {
		return mt.lang.d(a, "Number") && isFinite(a)
	};
	mt.lang.pb = function(a) {
		return mt.lang.d(a, "String")
	};
	mt.lang.h = function(a) {
		return a.replace ? a.replace(/'/g, "'0").replace(/\*/g, "'1").replace(/!/g, "'2") : a
	};
	mt.localStorage = {};
	mt.localStorage.I = function() {
		if (!mt.localStorage.i) try {
			mt.localStorage.i = document.createElement("input"),
			mt.localStorage.i.type = "hidden",
			mt.localStorage.i.style.display = "none",
			mt.localStorage.i.addBehavior("#default#userData"),
			document.getElementsByTagName("head")[0].appendChild(mt.localStorage.i)
		} catch(a) {
			return u
		}
		return s
	};
	mt.localStorage.set = function(a, b, d) {
		var g = new Date;
		g.setTime(g.getTime() + d || 31536E6);
		try {
			window.localStorage ? (b = g.getTime() + "|" + b, window.localStorage.setItem(a, b)) : mt.localStorage.I() && (mt.localStorage.i.expires = g.toUTCString(), mt.localStorage.i.load(document.location.hostname), mt.localStorage.i.setAttribute(a, b), mt.localStorage.i.save(document.location.hostname))
		} catch(f) {}
	};
	mt.localStorage.get = function(a) {
		if (window.localStorage) {
			if (a = window.localStorage.getItem(a)) {
				var b = a.indexOf("|"),
				d = a.substring(0, b) - 0;
				if (d && d > (new Date).getTime()) return a.substring(b + 1)
			}
		} else if (mt.localStorage.I()) try {
			return mt.localStorage.i.load(document.location.hostname),
			mt.localStorage.i.getAttribute(a)
		} catch(g) {}
		return t
	};
	mt.localStorage.remove = function(a) {
		if (window.localStorage) window.localStorage.removeItem(a);
		else if (mt.localStorage.I()) try {
			mt.localStorage.i.load(document.location.hostname),
			mt.localStorage.i.removeAttribute(a),
			mt.localStorage.i.save(document.location.hostname)
		} catch(b) {}
	};
	mt.sessionStorage = {};
	mt.sessionStorage.set = function(a, b) {
		if (window.sessionStorage) try {
			window.sessionStorage.setItem(a, b)
		} catch(d) {}
	};
	mt.sessionStorage.get = function(a) {
		return window.sessionStorage ? window.sessionStorage.getItem(a) : t
	};
	mt.sessionStorage.remove = function(a) {
		window.sessionStorage && window.sessionStorage.removeItem(a)
	};
	mt.da = {};
	mt.da.log = function(a, b) {
		var d = new Image,
		g = "mini_tangram_log_" + Math.floor(2147483648 * Math.random()).toString(36);
		window[g] = d;
		d.onload = d.onerror = d.onabort = function() {
			d.onload = d.onerror = d.onabort = t;
			d = window[g] = t;
			b && b(a)
		};
		d.src = a
	};
	mt.S = {};
	mt.S.Ha = function() {
		var a = "";
		if (navigator.plugins && navigator.mimeTypes.length) {
			var b = navigator.plugins["Shockwave Flash"];
			b && b.description && (a = b.description.replace(/^.*\s+(\S+)\s+\S+$/, "$1"))
		} else if (window.ActiveXObject) try {
			if (b = new ActiveXObject("ShockwaveFlash.ShockwaveFlash"))(a = b.GetVariable("$version")) && (a = a.replace(/^.*\s+(\d+),(\d+).*$/, "$1.$2"))
		} catch(d) {}
		return a
	};
	mt.S.ib = function(a, b, d, g, f) {
		return '<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" id="' + a + '" width="' + d + '" height="' + g + '"><param name="movie" value="' + b + '" /><param name="flashvars" value="' + (f || "") + '" /><param name="allowscriptaccess" value="always" /><embed type="application/x-shockwave-flash" name="' + a + '" width="' + d + '" height="' + g + '" src="' + b + '" flashvars="' + (f || "") + '" allowscriptaccess="always" /></object>'
	};
	mt.url = {};
	mt.url.g = function(a, b) {
		var d = a.match(RegExp("(^|&|\\?|#)(" + b + ")=([^&#]*)(&|$|#)", ""));
		return d ? d[3] : t
	};
	mt.url.lb = function(a) {
		return (a = a.match(/^(https?:)\/\//)) ? a[1] : t
	};
	mt.url.Ba = function(a) {
		return (a = a.match(/^(https?:\/\/)?([^\/\?#]*)/)) ? a[2].replace(/.*@/, "") : t
	};
	mt.url.X = function(a) {
		return (a = mt.url.Ba(a)) ? a.replace(/:\d+$/, "") : a
	};
	mt.url.M = function(a) {
		return (a = a.match(/^(https?:\/\/)?[^\/]*(.*)/)) ? a[2].replace(/[\?#].*/, "").replace(/^$/, "/") : t
	}; (function() {
		function a() {
			for (var a = u,
			d = document.getElementsByTagName("script"), g = d.length, g = 100 < g ? 100 : g, f = 0; f < g; f++) {
				var p = d[f].src;
				if (p && 0 === p.indexOf("https://hm.baidu.com/h")) {
					a = s;
					break
				}
			}
			return a
		}
		return h.va = a
	})();
	var A = h.va;
	h.z = {
		mb: "http://tongji.baidu.com/hm-web/welcome/ico",
		Q: "hm.baidu.com/hm.gif",
		ia: "baidu.com",
		Ma: "hmmd",
		Na: "hmpl",
		eb: "utm_medium",
		La: "hmkw",
		gb: "utm_term",
		Ja: "hmci",
		cb: "utm_content",
		Oa: "hmsr",
		fb: "utm_source",
		Ka: "hmcu",
		bb: "utm_campaign",
		r: 0,
		m: Math.round( + new Date / 1E3),
		V: Math.round( + new Date / 1E3) % 65535,
		protocol: "https:" === document.location.protocol ? "https:": "http:",
		F: A() || "https:" === document.location.protocol ? "https:": "http:",
		ob: 0,
		oa: 6E5,
		pa: 5,
		U: 1024,
		na: 1,
		w: 2147483647,
		ea: "cc cf ci ck cl cm cp cu cw ds ep et fl ja ln lo lt rnd si su v cv lv api sn ct u tt".split(" ")
	}; (function() {
		var a = {
			p: {},
			c: function(a, d) {
				this.p[a] = this.p[a] || [];
				this.p[a].push(d)
			},
			A: function(a, d) {
				this.p[a] = this.p[a] || [];
				for (var g = this.p[a].length, f = 0; f < g; f++) this.p[a][f](d)
			}
		};
		return h.B = a
	})(); (function() {
		function a(a, g) {
			var f = document.createElement("script");
			f.charset = "utf-8";
			b.d(g, "Function") && (f.readyState ? f.onreadystatechange = function() {
				if ("loaded" === f.readyState || "complete" === f.readyState) f.onreadystatechange = t,
				g()
			}: f.onload = function() {
				g()
			});
			f.src = a;
			var p = document.getElementsByTagName("script")[0];
			p.parentNode.insertBefore(f, p)
		}
		var b = mt.lang;
		return h.load = a
	})(); (function() {
		var a = mt.f,
		b = mt.lang,
		d = mt.event,
		g = mt.j,
		f = h.z,
		p = [],
		n = {
			fa: function() {
				c.ctrk && (d.c(document, "mouseup", n.ma()), d.c(window, "unload",
				function() {
					n.G()
				}), setInterval(function() {
					n.G()
				},
				f.oa))
			},
			ma: function() {
				return function(a) {
					a = n.ya(a);
					if ("" !== a) {
						var l = (f.F + "//" + f.Q + "?" + h.b.ca().replace(/ep=[^&]*/, "ep=" + encodeURIComponent(a))).length;
						l + (f.w + "").length > f.U || (l + encodeURIComponent(p.join("!") + (p.length ? "!": "")).length + (f.w + "").length > f.U && n.G(), p.push(a), (p.length >= f.pa || /\*a\*/.test(a)) && n.G())
					}
				}
			},
			ya: function(d) {
				var l = d.target || d.srcElement;
				if (0 === f.na) {
					var k = (l.tagName || "").toLowerCase();
					if ("embed" == k || "object" == k) return ""
				}
				var v;
				g.Z ? (v = Math.max(document.documentElement.scrollTop, document.body.scrollTop), k = Math.max(document.documentElement.scrollLeft, document.body.scrollLeft), k = d.clientX + k, v = d.clientY + v) : (k = d.pageX, v = d.pageY);
				d = n.Ca(d, l, k, v);
				var m = window.innerWidth || document.documentElement.clientWidth || document.body.offsetWidth;
				switch (c.align) {
				case 1:
					k -= m / 2;
					break;
				case 2:
					k -= m
				}
				m = [];
				m.push(k);
				m.push(v);
				m.push(d.Ra);
				m.push(d.Sa);
				m.push(d.Ua);
				m.push(b.h(d.Ta));
				m.push(d.hb);
				m.push(d.Ia); (l = "a" === (l.tagName || "").toLowerCase() ? l: a.xa(l)) ? (m.push("a"), m.push(b.h(encodeURIComponent(l.href)))) : m.push("b");
				return m.join("*")
			},
			Ca: function(d, l, k, b) {
				d = a.Fa(l);
				var m = 0,
				q = 0,
				f = 0,
				w = 0;
				if (l && (m = l.offsetWidth || l.clientWidth, q = l.offsetHeight || l.clientHeight, w = a.Da(l), f = w.left, w = w.top, "html" === (l.tagName || "").toLowerCase())) m = Math.max(m, l.clientWidth),
				q = Math.max(q, l.clientHeight);
				return {
					Ra: Math.round(100 * ((k - f) / m)),
					Sa: Math.round(100 * ((b - w) / q)),
					Ua: g.orientation,
					Ta: d,
					hb: m,
					Ia: q
				}
			},
			G: function() {
				0 !== p.length && (h.b.a.et = 2, h.b.a.ep = p.join("!"), h.b.k(), p = [])
			}
		};
		h.B.c("pv-b", n.fa);
		return n
	})(); (function() {
		function a() {
			return function() {
				h.b.a.nv = 0;
				h.b.a.st = 4;
				h.b.a.et = 3;
				h.b.a.ep = h.J.Ea() + "," + h.J.Aa();
				h.b.k()
			}
		}
		function b() {
			clearTimeout(y);
			var a;
			w && (a = "visible" == document[w]);
			z && (a = !document[z]);
			e = "undefined" == typeof a ? s: a;
			if ((!n || !l) && e && k) x = s,
			m = +new Date;
			else if (n && l && (!e || !k)) x = u,
			q += +new Date - m;
			n = e;
			l = k;
			y = setTimeout(b, 100)
		}
		function d(a) {
			var l = document,
			m = "";
			if (a in l) m = a;
			else for (var d = ["webkit", "ms", "moz", "o"], k = 0; k < d.length; k++) {
				var b = d[k] + a.charAt(0).toUpperCase() + a.slice(1);
				if (b in l) {
					m = b;
					break
				}
			}
			return m
		}
		function g(a) {
			if (! ("focus" == a.type || "blur" == a.type) || !(a.target && a.target != window)) k = "focus" == a.type || "focusin" == a.type ? s: u,
			b()
		}
		var f = mt.event,
		p = h.B,
		n = s,
		e = s,
		l = s,
		k = s,
		v = +new Date,
		m = v,
		q = 0,
		x = s,
		w = d("visibilityState"),
		z = d("hidden"),
		y;
		b(); (function() {
			var a = w.replace(/[vV]isibilityState/, "visibilitychange");
			f.c(document, a, b);
			f.c(window, "pageshow", b);
			f.c(window, "pagehide", b);
			"object" == typeof document.onfocusin ? (f.c(document, "focusin", g), f.c(document, "focusout", g)) : (f.c(window, "focus", g), f.c(window, "blur", g))
		})();
		h.J = {
			Ea: function() {
				return + new Date - v
			},
			Aa: function() {
				return x ? +new Date - m + q: q
			}
		};
		p.c("pv-b",
		function() {
			f.c(window, "unload", a())
		});
		return h.J
	})(); (function() {
		var a = mt.lang,
		b = h.z,
		d = h.load,
		g = {
			Qa: function(f) {
				if ((window._dxt === r || a.d(window._dxt, "Array")) && "undefined" !== typeof h.b) {
					var g = h.b.L();
					d([b.protocol, "//datax.baidu.com/x.js?si=", c.id, "&dm=", encodeURIComponent(g)].join(""), f)
				}
			},
			ab: function(d) {
				if (a.d(d, "String") || a.d(d, "Number")) window._dxt = window._dxt || [],
				window._dxt.push(["_setUserId", d])
			}
		};
		return h.qa = g
	})(); (function() {
		function a(a, d, b, m) {
			if (! (a === r || d === r || m === r)) {
				if ("" === a) return [d, b, m].join("*");
				a = String(a).split("!");
				for (var q, f = u,
				e = 0; e < a.length; e++) if (q = a[e].split("*"), String(d) === q[0]) {
					q[1] = b;
					q[2] = m;
					a[e] = q.join("*");
					f = s;
					break
				}
				f || a.push([d, b, m].join("*"));
				return a.join("!")
			}
		}
		function b(a) {
			for (var k in a) if ({}.hasOwnProperty.call(a, k)) {
				var e = a[k];
				d.d(e, "Object") || d.d(e, "Array") ? b(e) : a[k] = String(e)
			}
		}
		var d = mt.lang,
		g = mt.l,
		f = h.z,
		p = h.B,
		n = h.qa,
		e = {
			s: [],
			H: 0,
			$: u,
			o: {
				T: "",
				page: ""
			},
			init: function() {
				e.e = 0;
				p.c("pv-b",
				function() {
					e.ra();
					e.ta()
				});
				p.c("pv-d",
				function() {
					e.ua();
					e.o.page = ""
				});
				p.c("stag-b",
				function() {
					h.b.a.api = e.e || e.H ? e.e + "_" + e.H: "";
					h.b.a.ct = [decodeURIComponent(h.b.getData("Hm_ct_" + c.id) || ""), e.o.T, e.o.page].join("!")
				});
				p.c("stag-d",
				function() {
					h.b.a.api = 0;
					e.e = 0;
					e.H = 0
				})
			},
			ra: function() {
				var a = window._hmt || [];
				if (!a || d.d(a, "Array")) window._hmt = {
					id: c.id,
					cmd: {},
					push: function() {
						for (var a = window._hmt,
						l = 0; l < arguments.length; l++) {
							var m = arguments[l];
							d.d(m, "Array") && (a.cmd[a.id].push(m), "_setAccount" === m[0] && (1 < m.length && /^[0-9a-f]{32}$/.test(m[1])) && (m = m[1], a.id = m, a.cmd[m] = a.cmd[m] || []))
						}
					}
				},
				window._hmt.cmd[c.id] = [],
				window._hmt.push.apply(window._hmt, a)
			},
			ta: function() {
				var a = window._hmt;
				if (a && a.cmd && a.cmd[c.id]) for (var d = a.cmd[c.id], b = /^_track(Event|MobConv|Order|RTEvent)$/, m = 0, q = d.length; m < q; m++) {
					var f = d[m];
					b.test(f[0]) ? e.s.push(f) : e.P(f)
				}
				a.cmd[c.id] = {
					push: e.P
				}
			},
			ua: function() {
				if (0 < e.s.length) for (var a = 0,
				d = e.s.length; a < d; a++) e.P(e.s[a]);
				e.s = t
			},
			P: function(a) {
				var b = a[0];
				if (e.hasOwnProperty(b) && d.d(e[b], "Function")) e[b](a)
			},
			_setAccount: function(a) {
				1 < a.length && /^[0-9a-f]{32}$/.test(a[1]) && (e.e |= 1)
			},
			_setAutoPageview: function(a) {
				if (1 < a.length && (a = a[1], u === a || s === a)) e.e |= 2,
				h.b.Y = a
			},
			_trackPageview: function(a) {
				if (1 < a.length && a[1].charAt && "/" === a[1].charAt(0)) {
					e.e |= 4;
					h.b.a.et = 0;
					h.b.a.ep = "";
					h.b.N ? (h.b.a.nv = 0, h.b.a.st = 4) : h.b.N = s;
					var d = h.b.a.u,
					b = h.b.a.su;
					h.b.a.u = f.protocol + "//" + document.location.host + a[1];
					e.$ || (h.b.a.su = document.location.href);
					h.b.k();
					h.b.a.u = d;
					h.b.a.su = b
				}
			},
			_trackEvent: function(a) {
				2 < a.length && (e.e |= 8, h.b.a.nv = 0, h.b.a.st = 4, h.b.a.et = 4, h.b.a.ep = d.h(a[1]) + "*" + d.h(a[2]) + (a[3] ? "*" + d.h(a[3]) : "") + (a[4] ? "*" + d.h(a[4]) : ""), h.b.k())
			},
			_setCustomVar: function(a) {
				if (! (4 > a.length)) {
					var b = a[1],
					f = a[4] || 3;
					if (0 < b && 6 > b && 0 < f && 4 > f) {
						e.H++;
						for (var m = (h.b.a.cv || "*").split("!"), q = m.length; q < b - 1; q++) m.push("*");
						m[b - 1] = f + "*" + d.h(a[2]) + "*" + d.h(a[3]);
						h.b.a.cv = m.join("!");
						a = h.b.a.cv.replace(/[^1](\*[^!]*){2}/g, "*").replace(/((^|!)\*)+$/g, "");
						"" !== a ? h.b.setData("Hm_cv_" + c.id, encodeURIComponent(a), c.age) : h.b.Va("Hm_cv_" + c.id)
					}
				}
			},
			_setUserTag: function(b) {
				if (! (3 > b.length)) {
					var k = d.h(b[1]);
					b = d.h(b[2]);
					if (k !== r && b !== r) {
						var f = decodeURIComponent(h.b.getData("Hm_ct_" + c.id) || ""),
						f = a(f, k, 1, b);
						h.b.setData("Hm_ct_" + c.id, encodeURIComponent(f), c.age)
					}
				}
			},
			_setVisitTag: function(b) {
				if (! (3 > b.length)) {
					var f = d.h(b[1]);
					b = d.h(b[2]);
					if (f !== r && b !== r) {
						var g = e.o.T,
						g = a(g, f, 2, b);
						e.o.T = g
					}
				}
			},
			_setPageTag: function(b) {
				if (! (3 > b.length)) {
					var f = d.h(b[1]);
					b = d.h(b[2]);
					if (f !== r && b !== r) {
						var g = e.o.page,
						g = a(g, f, 3, b);
						e.o.page = g
					}
				}
			},
			_setReferrerOverride: function(a) {
				1 < a.length && (h.b.a.su = a[1].charAt && "/" === a[1].charAt(0) ? f.protocol + "//" + window.location.host + a[1] : a[1], e.$ = s)
			},
			_trackOrder: function(a) {
				a = a[1];
				d.d(a, "Object") && (b(a), e.e |= 16, h.b.a.nv = 0, h.b.a.st = 4, h.b.a.et = 94, h.b.a.ep = g.stringify(a), h.b.k())
			},
			_trackMobConv: function(a) {
				if (a = {
					webim: 1,
					tel: 2,
					map: 3,
					sms: 4,
					callback: 5,
					share: 6
				} [a[1]]) e.e |= 32,
				h.b.a.et = 93,
				h.b.a.ep = a,
				h.b.k()
			},
			_trackRTPageview: function(a) {
				a = a[1];
				d.d(a, "Object") && (b(a), a = g.stringify(a), 512 >= encodeURIComponent(a).length && (e.e |= 64, h.b.a.rt = a))
			},
			_trackRTEvent: function(a) {
				a = a[1];
				if (d.d(a, "Object")) {
					b(a);
					a = encodeURIComponent(g.stringify(a));
					var k = function(a) {
						var m = h.b.a.rt;
						e.e |= 128;
						h.b.a.et = 90;
						h.b.a.rt = a;
						h.b.k();
						h.b.a.rt = m
					},
					n = a.length;
					if (900 >= n) k.call(this, a);
					else for (var n = Math.ceil(n / 900), m = "block|" + Math.round(Math.random() * f.w).toString(16) + "|" + n + "|", q = [], p = 0; p < n; p++) q.push(p),
					q.push(a.substring(900 * p, 900 * p + 900)),
					k.call(this, m + q.join("|")),
					q = []
				}
			},
			_setUserId: function(a) {
				a = a[1];
				n.Qa();
				n.ab(a)
			}
		};
		e.init();
		h.ka = e;
		return h.ka
	})(); (function() {
		function a() {
			"undefined" === typeof window["_bdhm_loaded_" + c.id] && (window["_bdhm_loaded_" + c.id] = s, this.a = {},
			this.Y = s, this.N = u, this.init())
		}
		var b = mt.url,
		d = mt.da,
		g = mt.S,
		f = mt.lang,
		p = mt.cookie,
		n = mt.j,
		e = mt.localStorage,
		l = mt.sessionStorage,
		k = h.z,
		v = h.B;
		a.prototype = {
			O: function(a, b) {
				a = "." + a.replace(/:\d+/, "");
				b = "." + b.replace(/:\d+/, "");
				var d = a.indexOf(b);
				return - 1 < d && d + b.length === a.length
			},
			aa: function(a, b) {
				a = a.replace(/^https?:\/\//, "");
				return 0 === a.indexOf(b)
			},
			C: function(a) {
				for (var d = 0; d < c.dm.length; d++) if ( - 1 < c.dm[d].indexOf("/")) {
					if (this.aa(a, c.dm[d])) return s
				} else {
					var f = b.X(a);
					if (f && this.O(f, c.dm[d])) return s
				}
				return u
			},
			L: function() {
				for (var a = document.location.hostname,
				d = 0,
				b = c.dm.length; d < b; d++) if (this.O(a, c.dm[d])) return c.dm[d].replace(/(:\d+)?[\/\?#].*/, "");
				return a
			},
			W: function() {
				for (var a = 0,
				d = c.dm.length; a < d; a++) {
					var b = c.dm[a];
					if ( - 1 < b.indexOf("/") && this.aa(document.location.href, b)) return b.replace(/^[^\/]+(\/.*)/, "$1") + "/"
				}
				return "/"
			},
			Ga: function() {
				if (!document.referrer) return k.m - k.r > c.vdur ? 1 : 4;
				var a = u;
				this.C(document.referrer) && this.C(document.location.href) ? a = s: (a = b.X(document.referrer), a = this.O(a || "", document.location.hostname));
				return a ? k.m - k.r > c.vdur ? 1 : 4 : 3
			},
			getData: function(a) {
				try {
					return p.get(a) || l.get(a) || e.get(a)
				} catch(d) {}
			},
			setData: function(a, d, b) {
				try {
					p.set(a, d, {
						domain: this.L(),
						path: this.W(),
						K: b
					}),
					b ? e.set(a, d, b) : l.set(a, d)
				} catch(f) {}
			},
			Va: function(a) {
				try {
					p.set(a, "", {
						domain: this.L(),
						path: this.W(),
						K: -1
					}),
					l.remove(a),
					e.remove(a)
				} catch(d) {}
			},
			Za: function() {
				var a, d, b, f, e;
				k.r = this.getData("Hm_lpvt_" + c.id) || 0;
				13 === k.r.length && (k.r = Math.round(k.r / 1E3));
				d = this.Ga();
				a = 4 !== d ? 1 : 0;
				if (b = this.getData("Hm_lvt_" + c.id)) {
					f = b.split(",");
					for (e = f.length - 1; 0 <= e; e--) 13 === f[e].length && (f[e] = "" + Math.round(f[e] / 1E3));
					for (; 2592E3 < k.m - f[0];) f.shift();
					e = 4 > f.length ? 2 : 3;
					for (1 === a && f.push(k.m); 4 < f.length;) f.shift();
					b = f.join(",");
					f = f[f.length - 1]
				} else b = k.m,
				f = "",
				e = 1;
				this.setData("Hm_lvt_" + c.id, b, c.age);
				this.setData("Hm_lpvt_" + c.id, k.m);
				b = k.m === this.getData("Hm_lpvt_" + c.id) ? "1": "0";
				if (0 === c.nv && this.C(document.location.href) && ("" === document.referrer || this.C(document.referrer))) a = 0,
				d = 4;
				this.a.nv = a;
				this.a.st = d;
				this.a.cc = b;
				this.a.lt = f;
				this.a.lv = e
			},
			ca: function() {
				for (var a = [], d = this.a.et, b = 0, f = k.ea.length; b < f; b++) {
					var e = k.ea[b],
					g = this.a[e];
					"undefined" !== typeof g && "" !== g && ("tt" !== e || "tt" === e && 0 === d) && ("ct" !== e || "ct" === e && 0 === d) && a.push(e + "=" + encodeURIComponent(g))
				}
				switch (d) {
				case 0:
					a.push("sn=" + k.V);
					this.a.rt && a.push("rt=" + encodeURIComponent(this.a.rt));
					break;
				case 3:
					a.push("sn=" + k.V);
					break;
				case 90:
					this.a.rt && a.push("rt=" + this.a.rt)
				}
				return a.join("&")
			},
			$a: function() {
				this.Za();
				this.a.si = c.id;
				this.a.su = document.referrer;
				this.a.ds = n.Wa;
				this.a.cl = n.colorDepth + "-bit";
				this.a.ln = String(n.language).toLowerCase();
				this.a.ja = n.javaEnabled ? 1 : 0;
				this.a.ck = n.cookieEnabled ? 1 : 0;
				this.a.lo = "number" === typeof _bdhm_top ? 1 : 0;
				this.a.fl = g.Ha();
				this.a.v = "1.2.24";
				this.a.cv = decodeURIComponent(this.getData("Hm_cv_" + c.id) || "");
				this.a.tt = document.title || "";
				var a = document.location.href;
				this.a.cm = b.g(a, k.Ma) || "";
				this.a.cp = b.g(a, k.Na) || b.g(a, k.eb) || "";
				this.a.cw = b.g(a, k.La) || b.g(a, k.gb) || "";
				this.a.ci = b.g(a, k.Ja) || b.g(a, k.cb) || "";
				this.a.cf = b.g(a, k.Oa) || b.g(a, k.fb) || "";
				this.a.cu = b.g(a, k.Ka) || b.g(a, k.bb) || ""
			},
			init: function() {
				try {
					this.$a(),
					0 === this.a.nv ? this.Ya() : this.R(".*"),
					h.b = this,
					this.la(),
					v.A("pv-b"),
					this.Xa()
				} catch(a) {
					var b = [];
					b.push("si=" + c.id);
					b.push("n=" + encodeURIComponent(a.name));
					b.push("m=" + encodeURIComponent(a.message));
					b.push("r=" + encodeURIComponent(document.referrer));
					d.log(k.F + "//" + k.Q + "?" + b.join("&"))
				}
			},
			Xa: function() {
				function a() {
					v.A("pv-d")
				}
				this.Y ? (this.N = s, this.a.et = 0, this.a.ep = "", this.k(a)) : a()
			},
			k: function(a) {
				var b = this;
				b.a.rnd = Math.round(Math.random() * k.w);
				v.A("stag-b");
				var e = k.F + "//" + k.Q + "?" + b.ca();
				v.A("stag-d");
				b.ha(e);
				d.log(e,
				function(d) {
					b.R(d);
					f.d(a, "Function") && a.call(b)
				})
			},
			la: function() {
				var a = document.location.hash.substring(1),
				d = RegExp(c.id),
				f = -1 < document.referrer.indexOf(k.ia),
				e = b.g(a, "jn"),
				g = /^heatlink$|^select$|^pageclick$/.test(e);
				a && (d.test(a) && f && g) && (this.a.rnd = Math.round(Math.random() * k.w), a = document.createElement("script"), a.setAttribute("type", "text/javascript"), a.setAttribute("charset", "utf-8"), a.setAttribute("src", k.protocol + "//" + c.js + e + ".js?" + this.a.rnd), e = document.getElementsByTagName("script")[0], e.parentNode.insertBefore(a, e))
			},
			ha: function(a) {
				var b = l.get("Hm_unsent_" + c.id) || "",
				d = this.a.u ? "": "&u=" + encodeURIComponent(document.location.href),
				b = encodeURIComponent(a.replace(/^https?:\/\//, "") + d) + (b ? "," + b: "");
				l.set("Hm_unsent_" + c.id, b)
			},
			R: function(a) {
				var b = l.get("Hm_unsent_" + c.id) || "";
				b && (a = encodeURIComponent(a.replace(/^https?:\/\//, "")), a = RegExp(a.replace(/([\*\(\)])/g, "\\$1") + "(%26u%3D[^,]*)?,?", "g"), (b = b.replace(a, "").replace(/,$/, "")) ? l.set("Hm_unsent_" + c.id, b) : l.remove("Hm_unsent_" + c.id))
			},
			Ya: function() {
				var a = this,
				b = l.get("Hm_unsent_" + c.id);
				if (b) for (var b = b.split(","), f = function(b) {
					d.log(k.F + "//" + decodeURIComponent(b),
					function(b) {
						a.R(b)
					})
				},
				e = 0, g = b.length; e < g; e++) f(b[e])
			}
		};
		return new a
	})(); (function() {
		var a = mt.f,
		b = mt.event,
		d = mt.url,
		g = mt.l;
		try {
			if (window.performance && performance.timing && "undefined" !== typeof h.b) {
				var f = +new Date,
				p = function(a) {
					var b = performance.timing,
					d = b[a + "Start"] ? b[a + "Start"] : 0;
					a = b[a + "End"] ? b[a + "End"] : 0;
					return {
						start: d,
						end: a,
						value: 0 < a - d ? a - d: 0
					}
				},
				n = t;
				a.ba(function() {
					n = +new Date
				});
				var e = function() {
					var a, b, e;
					e = p("navigation");
					b = p("request");
					e = {
						netAll: b.start - e.start,
						netDns: p("domainLookup").value,
						netTcp: p("connect").value,
						srv: p("response").start - b.start,
						dom: performance.timing.domInteractive - performance.timing.fetchStart,
						loadEvent: p("loadEvent").end - e.start
					};
					a = document.referrer;
					var l = a.match(/^(http[s]?:\/\/)?([^\/]+)(.*)/) || [],
					x = t;
					b = t;
					if ("www.baidu.com" === l[2] || "m.baidu.com" === l[2]) x = d.g(a, "qid"),
					b = d.g(a, "click_t");
					a = x;
					e.qid = a != t ? a: "";
					b != t ? (e.bdDom = n ? n - b: 0, e.bdRun = f - b, e.bdDef = p("navigation").start - b) : (e.bdDom = 0, e.bdRun = 0, e.bdDef = 0);
					h.b.a.et = 87;
					h.b.a.ep = g.stringify(e);
					h.b.k()
				};
				b.c(window, "load",
				function() {
					setTimeout(e, 500)
				})
			}
		} catch(l) {}
	})(); (function() {
		var a = mt.j,
		b = mt.lang,
		d = mt.event,
		g = mt.l;
		if ("undefined" !== typeof h.b && (c.med || (!a.Z || 7 < a.Pa) && c.cvcc)) {
			var f, p, n, e, l = function(a) {
				if (a.item) {
					for (var b = a.length,
					d = Array(b); b--;) d[b] = a[b];
					return d
				}
				return [].slice.call(a)
			},
			k = function(a, b) {
				for (var d in a) if (a.hasOwnProperty(d) && b.call(a, d, a[d]) === u) return u
			},
			v = function(a, d) {
				var e = {};
				e.n = f;
				e.t = "clk";
				e.v = a;
				if (d) {
					var k = d.getAttribute("href"),
					l = d.getAttribute("onclick") ? "" + d.getAttribute("onclick") : t,
					m = d.getAttribute("id") || "";
					n.test(k) ? (e.sn = "mediate", e.snv = k) : b.d(l, "String") && n.test(l) && (e.sn = "wrap", e.snv = l);
					e.id = m
				}
				h.b.a.et = 86;
				h.b.a.ep = g.stringify(e);
				h.b.k();
				for (e = +new Date; 400 >= +new Date - e;);
			};
			if (c.med) p = "/zoosnet",
			f = "swt",
			n = /swt|zixun|call|chat|zoos|business|talk|kefu|openkf|online|\/LR\/Chatpre\.aspx/i,
			e = {
				click: function() {
					for (var a = [], b = l(document.getElementsByTagName("a")), b = [].concat.apply(b, l(document.getElementsByTagName("area"))), b = [].concat.apply(b, l(document.getElementsByTagName("img"))), d, e, f = 0, g = b.length; f < g; f++) d = b[f],
					e = d.getAttribute("onclick"),
					d = d.getAttribute("href"),
					(n.test(e) || n.test(d)) && a.push(b[f]);
					return a
				}
			};
			else if (c.cvcc) {
				p = "/other-comm";
				f = "other";
				n = c.cvcc.q || r;
				var m = c.cvcc.id || r;
				e = {
					click: function() {
						for (var a = [], b = l(document.getElementsByTagName("a")), b = [].concat.apply(b, l(document.getElementsByTagName("area"))), b = [].concat.apply(b, l(document.getElementsByTagName("img"))), d, e, f, g = 0, k = b.length; g < k; g++) d = b[g],
						n !== r ? (e = d.getAttribute("onclick"), f = d.getAttribute("href"), m ? (d = d.getAttribute("id"), (n.test(e) || n.test(f) || m.test(d)) && a.push(b[g])) : (n.test(e) || n.test(f)) && a.push(b[g])) : m !== r && (d = d.getAttribute("id"), m.test(d) && a.push(b[g]));
						return a
					}
				}
			}
			if ("undefined" !== typeof e && "undefined" !== typeof n) {
				var q;
				p += /\/$/.test(p) ? "": "/";
				var x = function(a, d) {
					if (q === d) return v(p + a, d),
					u;
					if (b.d(d, "Array") || b.d(d, "NodeList")) for (var e = 0,
					f = d.length; e < f; e++) if (q === d[e]) return v(p + a + "/" + (e + 1), d[e]),
					u
				};
				d.c(document, "mousedown",
				function(a) {
					a = a || window.event;
					q = a.target || a.srcElement;
					var d = {};
					for (k(e,
					function(a, e) {
						d[a] = b.d(e, "Function") ? e() : document.getElementById(e)
					}); q && q !== document && k(d, x) !== u;) q = q.parentNode
				})
			}
		}
	})(); (function() {
		var a = mt.f,
		b = mt.lang,
		d = mt.event,
		g = mt.l;
		if ("undefined" !== typeof h.b && b.d(c.cvcf, "Array") && 0 < c.cvcf.length) {
			var f = {
				ga: function() {
					for (var b = c.cvcf.length,
					g, e = 0; e < b; e++)(g = a.wa(decodeURIComponent(c.cvcf[e]))) && d.c(g, "click", f.sa())
				},
				sa: function() {
					return function() {
						h.b.a.et = 86;
						var a = {
							n: "form",
							t: "clk"
						};
						a.id = this.id;
						h.b.a.ep = g.stringify(a);
						h.b.k()
					}
				}
			};
			a.ba(function() {
				f.ga()
			})
		}
	})(); (function() {
		var a = mt.event,
		b = mt.l;
		if (c.med && "undefined" !== typeof h.b) {
			var d = +new Date,
			g = {
				n: "anti",
				sb: 0,
				kb: 0,
				clk: 0
			},
			f = function() {
				h.b.a.et = 86;
				h.b.a.ep = b.stringify(g);
				h.b.k()
			};
			a.c(document, "click",
			function() {
				g.clk++
			});
			a.c(document, "keyup",
			function() {
				g.kb = 1
			});
			a.c(window, "scroll",
			function() {
				g.sb++
			});
			a.c(window, "unload",
			function() {
				g.t = +new Date - d;
				f()
			});
			a.c(window, "load",
			function() {
				setTimeout(f, 5E3)
			})
		}
	})();
})();