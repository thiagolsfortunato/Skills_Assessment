using Microsoft.Owin.Hosting;
using Mock.Api;
using System;
using System.Net;
using System.Net.Sockets;

namespace PortalLexos.Api
{
    public class Program
    {
        private static void Main(string[] args)
        {
            var url = "http://localhost:9000";
            //var url = "http://" + GetLocalIPAddress() + ":9000";

            using (WebApp.Start<Startup>(url))
            {
                Console.WriteLine("It's working "+ url);
                Console.ReadLine();
            }
        }

        public static string GetLocalIPAddress()
        {
            var host = Dns.GetHostEntry(Dns.GetHostName());
            foreach (var ip in host.AddressList)
            {
                if (ip.AddressFamily == AddressFamily.InterNetwork)
                {
                    return ip.ToString();
                }
            }
            throw new Exception("Local IP Address Not Found!");
        }
    }
}
