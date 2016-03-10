using System;
using System.Web.Http;

namespace PortalLexos.Api.Controllers
{
    [RoutePrefix("api/mock")]
    public class EcommerceController : ApiController
    {
        [AllowAnonymous]
        [Route("test")]
        public string GetWhatever(string teste)
        {

            return DateTime.Now.ToString() + " funfo!!!!";
        }


        //public string GetQuestion()
        //{
        //    return @"

        //        [{ "number":3,"introduction":"https://www.youtube.com/watch?v=5t1FPSvpDko","question":"Qual Ã© este tipo de InteraÃ§Ã£o Humano Computador","answers":[{"code":1,"answer":"Interface GrÃ¡fica","competencies":[{"leadership":1,"workGroup":3}]},{"code":2,"answer":"Interface Linha de Comando","competencies":[{"leadership":0,"workGroup":0}]},{"code":3,"answer":"Interface Natural","competencies":[{"leadership":10,"workGroup":10,"communication":10}]},{"code":4,"answer":"Interface OrgÃ¢nica","competencies":[{"leadership":0}]}]}]
                
        //        ";
        //}

    }
}
