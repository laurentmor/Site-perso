/************************************************************************
# Ensemble de diverses routines JavaScript pour mon site et le blog engine
# TODO s�parer le code g�n�ral du code requis pour le blog engine
# Copyright (c) 2008 Laurent Morissette
#
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in
# all copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
# THE SOFTWARE.
 ************************************************************************/
   
var lRequestProcessor=null;
var lFenetre=window;
var lTexte=null;

/**
 * Function pour ex�cuter une requ�te c�t� serveur avec la m�thode AJAX
 * @param pRequestUrl
 * @param pMethod
 */
function executerRequeteAJAX(pRequestUrl,pMethod){
    
    try{
        if(lFenetre.XMLHttpRequest){
            lRequestProcessor=new XMLHttpRequest();
        }//FF 
        else if(lFenetre.ActiveXObject){
            lRequestProcessor=new ActiveXObject("Microsoft.XMLHTTP");//IE  
           
           
        }
        lRequestProcessor.open(pMethod,pRequestUrl,true);
        lRequestProcessor.send(null);
        
        
        
    }
    catch(e){
        
    }
    lRequestProcessor.onreadystatechange = processRequest;
    displayResultMessage("resultat",lTexte);


    
}
/***********************************
 * Traite la requ�te AJAX
 *
 ************************************/
function  processRequest(){
    if(lRequestProcessor.status==200){ 
        lTexte="<p class='messageOk'>La requ\ufffdte s'est d\ufffdroul�e correctement</p>";
    }
    if(lRequestProcessor.status==500){
        lTexte="<p class='messageErreur'>Une erreur interne au seerveur s'est produite</p>";
    }
    else{
        lTexte=lRequestProcessor.status;
    
    }
}

//document.
/************************************************
 *Function qui change la visibilit� d'un �l�ment
 *@param pId - le ID de l'�l�ment � changer
 **************************************************/
function switchLayerState(pId){
    var elem=document.getElementById(pId);
    var state=elem.style.visibility;
    
    if(state=="visible"){ 
        state="hidden";
        
        
    }
    else{ 
        state="visible";
        elem.style.visibility=state;
    }
    elem.style.visibility=state;
    
}                                        

/***********************************************************************
 *Function pour ajouter un �l�ment dans un select
 *Provient en partie de: http://www.mredkj.com/tutorials/tutorial005.html
 *@param pSelectId - le ID du select auquel ajouter l'option
 *@param pOptionText - le texte de l'option � ajouter
 *@param pOptionValue - La valeur de l'option � ajouter
 *************************************************************************/
function addOptionToSelect(pSelectId,pOptionText,pOptionValue ){
    var lOptionElement= document.createElement("option");
    var lRelatedSelect= document.getElementById(pSelectId);
    if(isNaN(lRelatedSelect)){
        alert("NaN");
    }   
    if( typeof lRelatedSelect=="select" ){
        lOptionElement.value=pOptionValue;
        lOptionElement.text=pOptionText;
    }
    try{
        lRelatedSelect.add(lOptionElement,null);//pour les browsers non-IE   
    }
    //Le reste
    catch(e){
        lRelatedSelect.add(lOptionElement); 
        
    }   
    
}

/*******************************************************************************
 * Function pour afficher un message d'�tat dans un page apr�s une requ�te AJAX*
 * @param pId - le id de div ou afficher le message 
 * @param pMessage - le message � afficher
 *******************************************************************************/
function displayResultMessage(pId,pMessage){
    var lMessageDiv=document.getElementById(pId);
                  
    lMessageDiv.innerHTML=pMessage;
}

