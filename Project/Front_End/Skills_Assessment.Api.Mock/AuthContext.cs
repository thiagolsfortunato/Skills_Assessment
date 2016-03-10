using PortalLexos.Api.Entities;
using Microsoft.AspNet.Identity.EntityFramework;
using System.Data.Entity;

namespace PortalLexos.Api
{
    public class AuthContext : IdentityDbContext<IdentityUser>
    {
        public AuthContext()
            : base("PortalLexosContext")
        {
     
        }

        public DbSet<Client> Clients { get; set; }
        public DbSet<RefreshToken> RefreshTokens { get; set; }
    }

}