var LANGS = [
	['Any Language',''],
    ['English (en)','en'],
    ['Dutch (nl)','nl'],
    ['German (de)','de'],
    ['Swedish (sv)','sv'],
    ['French (fr)','fr'],
    ['Italian (it)','it'],
    ['Russian (ru)','ru'],
    ['Spanish (es)','es'],
    ['Polish (pl)','pl'],
    ['Polish (pl)','pl'],

    ['Arabic (ar)','ar'],
    ['Basque (eu)','eu'],
    ['Bulgarian (bg)','bg'],
    ['Catalan (ca)','ca'],
    ['Cebuano (ceb)','ceb'],
    ['Chinese (zh)','zh'],
    ['Croatian (hr)','hr'],
    ['Czech (cs)','cs'],
    ['Danish (da)','da'],
    ['Dutch (nl)','nl'],
    ['English (en)','en'],
    ['Esperanto (eo)','eo'],
    ['Estonian (et)','et'],
    ['Finnish (fi)','fi'],
    ['French (fr)','fr'],
    ['Galician (gl)','gl'],
    ['German (de)','de'],
    ['Hebrew (he)','he'],
    ['Hindi (hi)','hi'],
    ['Hungarian (hu)','hu'],
    ['Indonesian (id)','id'],
    ['Italian (it)','it'],
    ['Japanese (ja)','ja'],
    ['Kazakh (kk)','kk'],
    ['Korean (ko)','ko'],
    ['Lithuanian (lt)','lt'],
    ['Malay (ms)','ms'],
    ['Minangkabau (min)','min'],
    ['Norwegian (Bokmal) (no)','no'],
    ['Norwegian (Nynorsk) (nn)','nn'],
    ['Persian (fa)','fa'],
    ['Polish (pl)','pl'],
    ['Portuguese (pt)','pt'],
    ['Romanian (ro)','ro'],
    ['Russian (ru)','ru'],
    ['Serbian (sr)','sr'],
    ['Slovak (sk)','sk'],
    ['Slovenian (sl)','sl'],
    ['Spanish (es)','es'],
    ['Swedish (sv)','sv'],
    ['Turkish (tr)','tr'],
    ['Ukrainian (uk)','uk'],
    ['Uzbek (uz)','uz'],
    ['Vietnamese (vi)','vi'],
    ['Volapuk (vo)','vo'],
    ['Waray-Waray (war)','war'],

    ['Abkhaz (ab)','ab'],
    ['Afar (aa)','aa'],
    ['Afrikaans (af)','af'],
    ['Akan (ak)','ak'],
    ['Albanian (sq)','sq'],
    ['Amharic (am)','am'],
    ['Arabic (ar)','ar'],
    ['Aragonese (an)','an'],
    ['Armenian (hy)','hy'],
    ['Assamese (as)','as'],
    ['Avaric (av)','av'],
    ['Avestan (ae)','ae'],
    ['Aymara (ay)','ay'],
    ['Azerbaijani (az)','az'],
    ['Bambara (bm)','bm'],
    ['Bashkir (ba)','ba'],
    ['Basque (eu)','eu'],
    ['Belarusian (be)','be'],
    ['Bengali; Bangla (bn)','bn'],
    ['Bihari (bh)','bh'],
    ['Bislama (bi)','bi'],
    ['Bosnian (bs)','bs'],
    ['Breton (br)','br'],
    ['Bulgarian (bg)','bg'],
    ['Burmese (my)','my'],
    ['Catalan; Valencian (ca)','ca'],
    ['Chamorro (ch)','ch'],
    ['Chechen (ce)','ce'],
    ['Chichewa; Chewa; Nyanja (ny)','ny'],
    ['Chinese (zh)','zh'],
    ['Chuvash (cv)','cv'],
    ['Cornish (kw)','kw'],
    ['Corsican (co)','co'],
    ['Cree (cr)','cr'],
    ['Croatian (hr)','hr'],
    ['Czech (cs)','cs'],
    ['Danish (da)','da'],
    ['Divehi; Dhivehi; Maldivian; (dv)','dv'],
    ['Dutch (nl)','nl'],
    ['Dzongkha (dz)','dz'],
    ['English (en)','en'],
    ['Esperanto (eo)','eo'],
    ['Estonian (et)','et'],
    ['Ewe (ee)','ee'],
    ['Faroese (fo)','fo'],
    ['Fijian (fj)','fj'],
    ['Finnish (fi)','fi'],
    ['French (fr)','fr'],
    ['Fula; Fulah; Pulaar; Pular (ff)','ff'],
    ['Galician (gl)','gl'],
    ['Georgian (ka)','ka'],
    ['German (de)','de'],
    ['Greek, Modern (el)','el'],
    ['Guarani (gn)','gn'],
    ['Gujarati (gu)','gu'],
    ['Haitian; Haitian Creole (ht)','ht'],
    ['Hausa (ha)','ha'],
    ['Hebrew (modern) (he)','he'],
    ['Herero (hz)','hz'],
    ['Hindi (hi)','hi'],
    ['Hiri Motu (ho)','ho'],
    ['Hungarian (hu)','hu'],
    ['Interlingua (ia)','ia'],
    ['Indonesian (id)','id'],
    ['Interlingue (ie)','ie'],
    ['Irish (ga)','ga'],
    ['Igbo (ig)','ig'],
    ['Inupiaq (ik)','ik'],
    ['Ido (io)','io'],
    ['Icelandic (is)','is'],
    ['Italian (it)','it'],
    ['Inuktitut (iu)','iu'],
    ['Japanese (ja)','ja'],
    ['Javanese (jv)','jv'],
    ['Kalaallisut, Greenlandic (kl)','kl'],
    ['Kannada (kn)','kn'],
    ['Kanuri (kr)','kr'],
    ['Kashmiri (ks)','ks'],
    ['Kazakh (kk)','kk'],
    ['Khmer (km)','km'],
    ['Kikuyu, Gikuyu (ki)','ki'],
    ['Kinyarwanda (rw)','rw'],
    ['Kyrgyz (ky)','ky'],
    ['Komi (kv)','kv'],
    ['Kongo (kg)','kg'],
    ['Korean (ko)','ko'],
    ['Kurdish (ku)','ku'],
    ['Kwanyama, Kuanyama (kj)','kj'],
    ['Latin (la)','la'],
    ['Luxembourgish, Letzeburgesch (lb)','lb'],
    ['Ganda (lg)','lg'],
    ['Limburgish, Limburgan, Limburger (li)','li'],
    ['Lingala (ln)','ln'],
    ['Lao (lo)','lo'],
    ['Lithuanian (lt)','lt'],
    ['Luba-Katanga (lu)','lu'],
    ['Latvian (lv)','lv'],
    ['Manx (gv)','gv'],
    ['Macedonian (mk)','mk'],
    ['Malagasy (mg)','mg'],
    ['Malay (ms)','ms'],
    ['Malayalam (ml)','ml'],
    ['Maltese (mt)','mt'],
    ['Maori (mi)','mi'],
    ['Marathi (Marathi) (mr)','mr'],
    ['Marshallese (mh)','mh'],
    ['Mongolian (mn)','mn'],
    ['Nauru (na)','na'],
    ['Navajo, Navaho (nv)','nv'],
    ['Norwegian Bokmal (nb)','nb'],
    ['North Ndebele (nd)','nd'],
    ['Nepali (ne)','ne'],
    ['Ndonga (ng)','ng'],
    ['Norwegian Nynorsk (nn)','nn'],
    ['Norwegian (no)','no'],
    ['Nuosu (ii)','ii'],
    ['South Ndebele (nr)','nr'],
    ['Occitan (oc)','oc'],
    ['Ojibwe, Ojibwa (oj)','oj'],
    ['Old Church Slavonic, Church Slavic, Church Slavonic, Old Bulgarian, Old Slavonic (cu)','cu'],
    ['Oromo (om)','om'],
    ['Oriya (or)','or'],
    ['Ossetian, Ossetic (os)','os'],
    ['Panjabi, Punjabi (pa)','pa'],
    ['Pali (pi)','pi'],
    ['Persian (Farsi) (fa)','fa'],
    ['Polish (pl)','pl'],
    ['Pashto, Pushto (ps)','ps'],
    ['Portuguese (pt)','pt'],
    ['Quechua (qu)','qu'],
    ['Romansh (rm)','rm'],
    ['Kirundi (rn)','rn'],
    ['Romanian, Moldavian(Romanian from Republic of Moldova) (ro)','ro'],
    ['Russian (ru)','ru'],
    ['Sanskrit (Samskrta) (sa)','sa'],
    ['Sardinian (sc)','sc'],
    ['Sindhi (sd)','sd'],
    ['Northern Sami (se)','se'],
    ['Samoan (sm)','sm'],
    ['Sango (sg)','sg'],
    ['Serbian (sr)','sr'],
    ['Scottish Gaelic; Gaelic (gd)','gd'],
    ['Shona (sn)','sn'],
    ['Sinhala, Sinhalese (si)','si'],
    ['Slovak (sk)','sk'],
    ['Slovene (sl)','sl'],
    ['Somali (so)','so'],
    ['Southern Sotho (st)','st'],
    ['South Azerbaijani (az)','az'],
    ['Spanish; Castilian (es)','es'],
    ['Sundanese (su)','su'],
    ['Swahili (sw)','sw'],
    ['Swati (ss)','ss'],
    ['Swedish (sv)','sv'],
    ['Tamil (ta)','ta'],
    ['Telugu (te)','te'],
    ['Tajik (tg)','tg'],
    ['Thai (th)','th'],
    ['Tigrinya (ti)','ti'],
    ['Tibetan Standard, Tibetan, Central (bo)','bo'],
    ['Turkmen (tk)','tk'],
    ['Tagalog (tl)','tl'],
    ['Tswana (tn)','tn'],
    ['Tonga (Tonga Islands) (to)','to'],
    ['Turkish (tr)','tr'],
    ['Tsonga (ts)','ts'],
    ['Tatar (tt)','tt'],
    ['Twi (tw)','tw'],
    ['Tahitian (ty)','ty'],
    ['Uyghur, Uighur (ug)','ug'],
    ['Ukrainian (uk)','uk'],
    ['Urdu (ur)','ur'],
    ['Uzbek (uz)','uz'],
    ['Venda (ve)','ve'],
    ['Vietnamese (vi)','vi'],
    ['Volapuk (vo)','vo'],
    ['Walloon (wa)','wa'],
    ['Welsh (cy)','cy'],
    ['Wolof (wo)','wo'],
    ['Western Frisian (fy)','fy'],
    ['Xhosa (xh)','xh'],
    ['Yiddish (yi)','yi'],
    ['Yoruba (yo)','yo'],
    ['Zhuang, Chuang (za)','za'],
    ['Zulu (zu)','zu']
];

