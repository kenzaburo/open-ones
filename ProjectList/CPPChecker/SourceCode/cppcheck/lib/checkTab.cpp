//---------------------------------------------------------------------------
// Check for Tab character using
//---------------------------------------------------------------------------
#include <iostream>
#include "checkTab.h"
#include "symboldatabase.h"

//---------------------------------------------------------------------------

// Register this check class (by creating a static instance of it)
namespace
{
    static CheckTab instance;
}


void CheckTab::checkTab()
{
// Thach debug
    std::cout << "CheckTab.checkTab:style=" << _settings->isEnabled("style") << std::endl;

    if (!_settings->isEnabled("style"))
        return;

    for (const Token *tok = _tokenizer->tokens(); tok; tok = tok->next())
    {
        // Thach debug
        std::cout << "Token:" << tok->str() << std::endl;


    }
}
