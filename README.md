# SCD_lab3

Sa se implementeze o aplicatie care compara preturile unor produse din doua surse.
Aplicatia presupune construirea unui sistem pentru extragerea preturilor unor casti audio din urmatoarele pagini:
http://www.cel.ro/casti/
Preturile arata astfel
<b itemprop="price" content="129">129</b>
Id
<div class="stoc_list"><span id="s113989-0"><b class="info_stoc in_stoc">in stoc</b></span></div> // nu se mai termina cu -0 (au schimbat de black friday)
URL si Titlul
<h4 class="productTitle"><a href="http://www.cel.ro/casti/casti-cu-microfon-sennheiser-pc-8-interfata-usb-pMCMwPT0n-l/" class="productListing-data-b product_link product_name"><span itemprop="name">Casti cu microfon Sennheiser PC 8 interfata USB</span></a></h4>
https://www.emag.ro/casti-pc/c?ref=hp_menu_quick-nav_23_649&type=category
Pretul
<p class="product-new-price">30<sup>99</sup> <span>Lei</span></p>
Id-ul se gaseste in campul value al inputului hidden cu name=”product[]”
<input type="hidden" name="product[]" value="555373">
URL si Titlul
<h2 class="card-body product-title-zone">
                        <a href="https://www.emag.ro/casti-cu-microfon-a-negru-shg1/pd/DXHB2YBBM/" data-ref="" class="product-title js-product-url" data-zone="title">Casti cu microfon A+ SHG1, Negru</a>
                    </h2>

Cerinte:
1. Se vor downloada x pagini din fiecare site cate o pagina din fiecare site per thread (in total x thread-uri). Unde x este 121. Extrage din xml-ul rezultat pentru fiecare produs: titlu, link, pret, id. 
2. Fiecare fir de executie trebuie sa parseze pagina alocata lui din ambele surse(emag si cel) si sa salveze informatiile intr-o lista  de casti COMUNA TUTUROR FIRELOR DE EXECUTIE de forma :
{listaCasti: [
	{Titlu: casti1,
	Id: idCasti1,
	listaPreturi:[
	{link: u
rl1Casti1,
pret:pret1Casti1},
{link: url2Casti1,
pret:pret2Casti1}
           ]
         },
        {Titlu: casti2,
	Id: idCasti2,
	listaPreturi:[
	{link: url1Casti2,
pret:pret1Casti2}, 
{link: url2Casti2,
pret:pret2Casti2}
           ]
         }
]}
Utilizati algoritmul lui Dekker sau Peterson pentru a evita conflictele de scriere din sectiunea critica. 
3. La final, pentru a demonstra integritatea listei, se afiseaza cel mai mic pret si site-ul pe care se gaseste pentru fiecare produs in parte.
