
//---------------------------------------------------------------------------
#ifndef checkTabH
#define checkTabH
//---------------------------------------------------------------------------

#include "config.h"
#include "check.h"
#include "mathlib.h"

/// @addtogroup Checks
/// @{

/**
 * @brief Check for assignment / condition mismatches
 */

class CPPCHECKLIB CheckTab : public Check {
public:
    /** This constructor is used when registering the CheckTab */
    CheckTab() : Check(myName()) {
    }

    /** This constructor is used when running checks. */
    CheckTab(const Tokenizer *tokenizer, const Settings *settings, ErrorLogger *errorLogger)
        : Check(myName(), tokenizer, settings, errorLogger) {
    }

    /** @brief Run checks against the simplified token list */
    void runSimplifiedChecks(const Tokenizer *tokenizer, const Settings *settings, ErrorLogger *errorLogger) {
        CheckTab checkTab(tokenizer, settings, errorLogger);
        checkTab();
    }

    /** check Tab character */
    void checkTab();

private:
    static std::string myName() {
        return "Tab character";
    }

    std::string classInfo() const {
        return "Check using Tab character.\n"
               ;
    }
};

/// @}
//---------------------------------------------------------------------------
#endif
