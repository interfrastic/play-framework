# GNU Privacy Guard (GPG)

## Installing GPG on macOS

1. Install the GPG Suite (this works better than installing GPG using a tool
   like [Homebrew](https://brew.sh)) from the GPGTools download page:

   https://gpgtools.org/#gpgsuite

   When the installation finishes, fill out the __Generate a new key pair__
   dialog box as follows and press __Generate Key__:
   * Name: ___your full name___
   * Email: ___your primary e-mail address___
   * Password: ___password generated in and copied from your password
     manager___
   * Confirm: ___password retyped manually___
   * Advanced options
     * Comment: ___your primary e-mail service; for example,___ __Gmail__
       ___or___ __iCloud__
     * Key type: __RSA and RSA (default)__
     * Length: __4096__
     * Key expires: ___unchecked___
     * Expiration date: ___ignored; accept default value___
1. If your password is a randomly generated Diceware-style pass phrase, you
   may see a __Simple password__ warning dialog box; dismiss this with
   __Continue with simple password__.
1. You may see the following dialog box pop up:
   > We need to generate a lot of random bytes. It is a good idea to perform
   > some other action (type on the keyboard, move the mouse, utilize the
   > disks) during the prime generation; this gives the random number
   > generator a better chance to gain enough entropy.
   This may disappear on its own if you comply.
1. When the __Your key was created successfully__ dialog box appears, press
   __No, Thanks!__ to skip the upload of your public key.
1. To view other user IDs (each of which consists of a name, e-mail address,
   and comment) associated with your key pair, select it in the __GPG
   Keychain__ window, press __Details__, then select the __User IDs__ tab. Do
   not use this interface to add a new user ID because it will not allow you
   to specify the content of the comment field.
1. Before adding user IDs on the command line, exit the GPG Keychain
   application. Changes made with the `gpg` command-line tool do not show up
   in GPG Keychain application until the next time it is launched after the
   changes are made.
1. When using the `gpg` command-line tool, a __Pinentry Mac__ dialog box may
   appear; if this happens, fill it out as follows and press __OK__:
    * Passphrase: ___password you used to generate the key pair___
    * Show typing: ___unchecked___
    * Save in Keychain: ___checked___

## Adding user IDs for GitHub and other services

After creating the key pair in the GPG Keychain macOS app, use the `gpg`
command-line tool in a Terminal window to add user IDs for GitHub and other
services.

1. Start `gpg` in interactive editing mode, specifying the primary e-mail
address you used to generate the original key-value pair; for example,
`gpg --edit-key nobody@example.com`.
1. Enter `adduid`, fill in the fields, and enter `o` when everything is correct:

   * Real name: ___exact name you used to create your GitHub account___
   * Email address: ___your GitHub private e-mail; for example,___
     `1234567+nobody@users.noreply.github.com`
   * Comment: `GitHub`

   Do not be concerned about the fact that the additional user IDs are marked as
   having an “unknown” trust level; these will change to “ultimate” (completely
   trusted) the next time you list the secret key with the `gpg
   --list-secret-keys --keyid-format LONG` command.
1. Repeat the previous step for any additional e-mail addresses you wish to use,
   entering the name of the service (for example, `Gmail` or `iCloud`)
   instead of `GitHub` in the comment.
1. Enter `save` to save the changes and exit interactive editing mode.

## Setting Up All Repositories for Signing

If you wish to sign all of your repositories the same way, then include the
`--global` flag when configuring Git commit signing:

```console
$ git config --global user.name 'No Body'
$ git config --global user.email 1234567+nobody@users.noreply.github.com
$ git config --global user.signingkey DEADBEEFDEADBEEF
$ git config --global commit.gpgsign true
```

## Setting Up Just One Repository for Signing

If you wish to set up signing on a per-repository basis, then change to a
directory in the repository first and omit the `--global` flag when configuring
Git commit signing:

```console
$ cd ~/git/repository
$ git config user.name 'No Body'
$ git config user.email 1234567+nobody@users.noreply.github.com
$ git config user.signingkey DEADBEEFDEADBEEF
$ git config commit.gpgsign true
```
